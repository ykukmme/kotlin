package fastcampus.aop.part2.chapter02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    //버튼들 연결하기 by lazy는 변수를 처음 부를때 딱 한번만 초기화
    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }

    private val addButton: Button by lazy {
        findViewById<Button>(R.id.addButton)
    }

    private val runButton: Button by lazy {
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val numberTextView: List<TextView> by lazy {
        listOf<TextView>(
            findViewById<TextView>(R.id.textView1),
            findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView4),
            findViewById<TextView>(R.id.textView5),
            findViewById<TextView>(R.id.textView6)
        )
    }

    private var didRun = false

    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //NumberPicker의 최소값 최대값 지정
        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initclearButton()
    }

    //자동 생성 버튼을 눌렀을때의 함수
    private fun initRunButton() {
        runButton.setOnClickListener {
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed { index, i ->
                val textView = numberTextView[index]

                textView.text = i.toString()
                textView.isVisible = true

                setNumberBack(i, textView)
            }

        }
    }

    private fun initAddButton(){

        addButton.setOnClickListener {
            //예외처리
            if (didRun){
                Toast.makeText(this, "초기화 후에 시도하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //로또 번호 생성기라 임의로 선택 가능한 번호는 최대 5개까지만 선택 가능
            if (pickNumberSet.size >= 5){
                Toast.makeText(this, "번호는 5개까지만 선택 가능.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //선택한 번호가 중복일 경우
            if (pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this, "이미 선택한 번호입니다다.", Toast.LENGTH_SHORT).show()
               return@setOnClickListener
            }

            val textView = numberTextView[pickNumberSet.size]
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            setNumberBack(numberPicker.value, textView)

            pickNumberSet.add(numberPicker.value)
        }
    }

    //숫자 뒤에 원형 모양의 색을 정하는 함수
    private fun setNumberBack(number:Int, textView: TextView) {
        when(number) {
            in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_yello)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_green)
            else -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_gray)
        }
    }

    //초기화 버튼을 눌렀을때
    private fun initclearButton() {
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextView.forEach {
                it.isVisible = false
            }

            didRun = false
        }
    }

    //번호를 랜덤하게 만드는 함수
    private fun  getRandomNumber(): List<Int> {
        //리스트를 선언하고는 1~45까지의 숫자들을 전부 리스터에 넣는다
        val numberList = mutableListOf<Int>()
            .apply {
                for (i in 1..45) {
                    if (pickNumberSet.contains(i)){
                        continue
                    }
                    this.add(i)
                }
            }

        //리스트를 마구잡이로 섞는다
        numberList.shuffle()

        //새로운 리스트를 만들어서 이미 골라진 숫자와 그 숫자의 수만큼을 뺀 나머지 숫자를 처음부터 그 리스트에 넣기
        val newList = pickNumberSet.toList() + numberList.subList(0, 6 - pickNumberSet.size)

        return newList.sorted()
    }
}