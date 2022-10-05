package fastcampus.aop.part2.cal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.room.Room
import fastcampus.aop.part2.cal.model.History

class MainActivity : AppCompatActivity() {

    private val expressionTextView: TextView by lazy {
        findViewById<TextView>(R.id.expressionTextView)
    }

    private val resultTextView: TextView by lazy {
        findViewById<TextView>(R.id.resultTextView)
    }

    private val historyLayout: View by lazy {
        findViewById<View>(R.id.historyLayout)
    }

    private val historyLinearLayout: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.historyLinearLayout)
    }

    lateinit var database: AppDatabase

    //연산자가 입력되었는지를 판별하기 위해 만든 변수
    private var isOperator = false
    private var hasOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "historyDB"
        ).build()
    }

    fun ButtonClicked(v: View) {
        when (v.id) {
            R.id.Button0 -> numberButtonClicked("0")
            R.id.Button1 -> numberButtonClicked("1")
            R.id.Button2 -> numberButtonClicked("2")
            R.id.Button3 -> numberButtonClicked("3")
            R.id.Button4 -> numberButtonClicked("4")
            R.id.Button5 -> numberButtonClicked("5")
            R.id.Button6 -> numberButtonClicked("6")
            R.id.Button7 -> numberButtonClicked("7")
            R.id.Button8 -> numberButtonClicked("8")
            R.id.Button9 -> numberButtonClicked("9")
            R.id.Button_plus -> operatorButtonClicked("+")
            R.id.Button_minus -> operatorButtonClicked("-")
            R.id.Button_multi -> operatorButtonClicked("*")
            R.id.Button_divider -> operatorButtonClicked("/")
            R.id.Button_modulo -> operatorButtonClicked("%")
        }
    }

    private fun numberButtonClicked(number: String) {

        if (isOperator) {
            expressionTextView.append(" ")
        }

        isOperator = false

        val expressionText = expressionTextView.text.split(" ")

        if (expressionText.isNotEmpty() && expressionText.last().length >= 15) {
            Toast.makeText(this, "15자리 까지만 사용할 수 있습니다", Toast.LENGTH_SHORT).show()
            return
        } else if (expressionText.last().isEmpty() && number == "0") {
            Toast.makeText(this, "0은 제일 앞에 올 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        expressionTextView.append(number)

        resultTextView.text = calculateExpression()
    }

    private fun operatorButtonClicked(operator: String) {

        if (expressionTextView.text.isEmpty()) {
            return
        }

        when {
            isOperator -> {
                val text = expressionTextView.text.toString()
                expressionTextView.text = text.dropLast(1) + operator
            }

            hasOperator -> {
                Toast.makeText(this, "연산자는 한 번만 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> {
                expressionTextView.append(" $operator")
            }
        }

        //계산기 화면에 표시되는 문자들 중에 연산자만 초록색으로 보이게 하는 코드
        val ssb = SpannableStringBuilder(expressionTextView.text)
        ssb.setSpan(
            ForegroundColorSpan(getColor(R.color.green)), expressionTextView.text.length - 1,
            expressionTextView.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        expressionTextView.text = ssb

        isOperator = true
        hasOperator = true
    }

    fun resultButtonClicked(v: View) {
        val expressionTexts = expressionTextView.text.split(" ")

        if (expressionTextView.text.isEmpty() || expressionTexts.size ==1) { //처음 숫자만 입력된 경우
            return
        }

        if (expressionTexts.size != 3 && hasOperator) { //숫자와 연산자만 입력된 경우
            Toast.makeText(this, "수식을 완성하고 눌러주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) {
            Toast.makeText(this, "오류 발생.", Toast.LENGTH_SHORT).show()
            return
        }

        val expressionText = expressionTextView.text.toString()
        val resultText = calculateExpression()

        //DB에 넣어주는 부분(DB와 관련된 모든 과정은 메인 Thread말고 별도의 Thread에서 해야함)
        Thread(Runnable{
            database.historyDao().insertHistory(History(null, expressionText, resultText))
        }).start()

        resultTextView.text = ""
        expressionTextView.text = resultText

        isOperator = false
        hasOperator = false

    }

    private fun calculateExpression(): String { // 연산을 하는 함수
        val expressionTexts = expressionTextView.text.split(" ")

        //예외처리 (계산식은 숫자, 연산자, 숫자로 구성되기에 .size 는 3이 되는게 맞다)
        if (hasOperator.not() || expressionTexts.size != 3) {
            return ""
        } else if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) {
            return ""
        }

        val exp1 = expressionTexts[0].toBigInteger()
        val exp2 = expressionTexts[2].toBigInteger()
        val op = expressionTexts[1]

        return when (op) {
            "+" -> (exp1 + exp2).toString()
            "-" -> (exp1 - exp2).toString()
            "*" -> (exp1 * exp2).toString()
            "/" -> (exp1 / exp2).toString()
            "%" -> (exp1 % exp2).toString()
            else -> ""
        }

    }

    fun historyButtonClicked(v: View) {
        historyLayout.isVisible = true
        historyLinearLayout.removeAllViews()

        Thread(Runnable {
            //DB에서 값들을 하나씩 꺼내오기(DB 순서는 최신껏이 밑에 저장되기에 최신껏을 위에 보이게 하기 위해서 리버스 사용)
            database.historyDao().getAll().reversed().forEach {
                //UI는 메인Thread에서 처리를 해야하기에 이용하는 메소드
                runOnUiThread{
                    val historyView = LayoutInflater.from(this).inflate(R.layout.history_row, null, false)
                    historyView.findViewById<TextView>(R.id.expressionTextView).text = it.expression
                    historyView.findViewById<TextView>(R.id.resultTextView).text = "= ${it.result}"

                    historyLinearLayout.addView(historyView)
                }
            }
        }).start()

    }

    fun historyClearButtonClicked(v: View) {

        //뷰 초기화
        historyLinearLayout.removeAllViews()

        //DB에서 기록 삭제
        Thread(Runnable {
            database.historyDao().deleteAll()
        }).start()
    }

    fun closeHistoryButtonClicked(v: View) {
        //historyLayout을 안보이게 함
        historyLayout.isVisible = false
    }

    //초기화 버튼이기에 텍스트뷰와 상태값 변수들을 다 초기값으로 바꿈
    fun clearButtonClicked(v: View) {
        expressionTextView.text = ""
        resultTextView.text = ""
        isOperator = false
        hasOperator = false
    }
}

//객체를 확장하는 함수 만들기 객체.함수 이름 형식으로 만듬
fun String.isNumber(): Boolean { //객체가 숫자열인지 판별하는 함수
    return try { 
        this.toBigInteger() //크기 제한이 없는 Integer
        true
    } catch (e: NumberFormatException) {
        false
    }
}