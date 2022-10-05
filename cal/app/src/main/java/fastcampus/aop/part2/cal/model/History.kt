package fastcampus.aop.part2.cal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//데이터 항목을 정의하는 데이터 클래스(테이블 생성)
@Entity
data class History (
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "expression") val expression: String?,
    @ColumnInfo(name = "result") val result: String?
    )