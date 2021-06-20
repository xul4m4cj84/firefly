package com.example.firefly

import android.util.Log
import androidx.lifecycle.*
import com.example.firefly.database.Bug
import com.example.firefly.database.BugDatabaseDao
import com.example.firefly.network.GetService
import com.example.firefly.network.TemperatureEntity
import com.example.firefly.network.asTemperatureEntity

import kotlinx.coroutines.launch

class MyViewModel(dataSource: BugDatabaseDao) : ViewModel() {
    private val database = dataSource

    val bugList = MediatorLiveData<List<Bug>>()

    val selectedBug = MutableLiveData<Bug>()

    val cityWeather = MutableLiveData<TemperatureEntity?>()

//    val cityList : Array<String> = application.resources.getStringArray(R.array.city_array)

    val cityList = arrayOf(
        "花蓮縣", "台東縣", "宜蘭縣", "屏東縣", "台東縣", "高雄市",
        "雲林縣", "彰化縣", "臺南市", "嘉義縣", "嘉義市", "臺中市", "臺北市",
        "新北市", "新竹縣", "新竹市", "基隆市", "苗栗縣", "桃園市", "南投縣", "澎湖縣",
        "金門縣", "連江縣"
    )


    init{
        getAllBug()
    }

    fun initDB(){
        viewModelScope.launch {
            database.insertBug(
                Bug(
                    "南投縣","熠螢亞科", "紅胸黑翅螢", R.drawable.photo0_0, "雄蟲體長10.0～11.5mm；頭部黑色，觸角絲狀；前胸背板桃紅色；前翅黑色；腹部末端有2節乳白色發光器，第一節為長橢圓形，第二節為半圓形。雌蟲體型略較雄蟲大，外型與雄蟲相似，僅有1枚發光器。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "南投縣","熠螢亞科", "紋胸黑翅螢", R.drawable.photo0_1, "體長5-6mm，前胸背板橙黃色，中央近基部黑色，翅鞘黑色密布短毛，低海拔型個體小盾片黑色，而中海拔則為橙色，雄蟲發光器2節。外觀近似擬紋螢，體型較小，雌蟲短翅。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "南投縣","熠螢亞科", "黑翅晦螢", R.drawable.photo0_2, "雄蟲體長7.6~8mm，頭部黑色，觸角絲狀，前胸背板橙黃色，前翅黑色，腹部末端有二節乳白色發光器，第一節為長橢圓形，第二節為半圓形。雌蟲體型略較雄蟲大，外型與雄蟲相似，僅有一節發光器。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "南投縣","熠螢亞科", "大晦螢(大端黑螢)", R.drawable.photo0_3, "雄蟲體長11.2～12.0公釐；體橙黃色；頭部黑色，觸角絲狀；前翅末端具有黑色斑塊；腹部末端有2節乳白色發光器，第一節為長橢圓形，第二節為半圓形。雌蟲體型略較雄蟲大，外型與雄蟲相似，僅有1枚發光器。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "花蓮縣","熠螢亞科", "黃肩脈翅螢", R.drawable.photo0_4, "體長6-8mm，複眼大，黑色，前胸背板橙黃褐色，左右各有一枚不明顯的褐斑，側角尖，小楯板橙黃色，翅鞘黑色密布刻點。本屬有8種，翅鞘近側緣各有一條稜狀的隆突，本種翅肩及翅面後緣黃色，分布於低海拔山區，南部及花蓮地區5-8月份出現，夜行性，雄蟲發光器2節，幼蟲黑色，末節左右各有一枚白色斑，夜晚也會發光，這種光通常是警戒性用來驅敵嚇退天敵。","花蓮縣"
                )
            )
            database.insertBug(
                Bug(
                    "台東縣","熠螢亞科", "黃脈翅螢", R.drawable.photo0_5, "雄蟲體長4.0～5.2mm；頭部黑色，觸角絲狀；前胸背板橙黃色；前翅橙黃色，翅側緣處有一條縱向隆脈，末端有黑色斑塊；腹部末端有2 節乳白色發光器，第一節為長橢圓形，第二節為半圓形。雌蟲體型略較雄蟲大，外型與雄蟲相似，且僅有1枚發光器。","台東縣"
                )
            )
            database.insertBug(
                Bug(
                    "南投縣","窗螢亞科", "山窗螢", R.drawable.photo1_0, "雄蟲體長18.4～21.8公釐，較臺灣窗螢雄蟲大；前胸背板半圓形，橙黃色，前緣有2枚腎形透明斑；前翅黑色，翅緣有橙黃色細紋，外側細紋較臺灣窗螢細小；腹部末端有2枚乳白色長橢圓形發光器。雌蟲體長26.5～28.3公釐；體橙黃色；翅退化為翅芽狀，前翅黑色，外緣有橙黃色細紋；腹部末端有4枚點狀發光器。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "南投縣","窗螢亞科", "紅胸窗螢", R.drawable.photo1_1, "雄蟲體長12.3～13.7公釐；觸角鋸齒狀；前胸背板呈半圓形、黑色，前緣部有2枚明顯的腎形透明斑，中後方有1枚紅色方形斑塊；前翅黑色；腹部末端無明顯發光器。雌蟲體長15.1～17.0公釐，體橙黃色，翅完全退化，不發光。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "南投縣","窗螢亞科", "蓬萊短角窗螢", R.drawable.photo1_2, "雄蟲體長10.3～14.5公釐；觸角絲狀、黑色；前胸背板為乳白色，呈半圓形，略為半透明，前緣有2 枚弧型的透明斑塊，中後方有紅色斑塊；前翅為黑色；腹部末端有2節不規則長橢圓形發光器。雌蟲體長18.3～20.6公釐，體橙黃色，前胸背板有紅色斑塊，前翅黑色，且退化為翅芽狀，腹部末端有2枚發光器。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "南投縣","窗螢亞科", "橙螢", R.drawable.photo1_3, "中大型的螢火蟲，因背面呈黃橙色而得名。雄蟲體長約13~16mm，複眼很大，佔了頭部的大部分體積，從腹面看則兩個卵圓形的複眼幾乎相接；前胸背板前方，也就是眼睛的上方，具有一片大形的透明隆起區；腹部腹面末端有兩排發光器。雌蟲是幼態型(neotenic, paedomorphic)，體型較雄蟲大(一般在2cm以上)，無後翅，不能飛行；腹部膨大，末端有兩排較不完整的發光器，能發出光點或整排光。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "南投縣","窗螢亞科", "赤腹櫛角螢", R.drawable.photo1_4, "雄蟲體長16.4~18.0公釐；頭部黑色，觸角黑色，呈櫛齒狀；前胸背板略呈半圓形，覆於頭部上方，呈紅色，背板前緣略向上翻起，且略微透明化；前翅黑色；足黑色；腹部紅色，發光器呈點狀。雌蟲體型略較雄蟲大，外型與雄蟲相似，但觸角接近鋸齒狀。","南投縣"
                )
            )
            database.insertBug(
                Bug(
                    "屏東縣","窗螢亞科", "黃脈翅螢", R.drawable.photo1_5, "雄蟲背面黑橄欖綠色，體長13~16mm，頭部具有很大的複眼，兩眼在腹面幾乎相接；觸角短，約與頭部等寬，略呈鋸齒狀；前胸背板蓋住頭部背方，但是沒有明顯的窗室。腹部末端一到兩節常外露於翅鞘外。腹面為黃灰色，腹節寬大，側方如葉片狀前後交疊。雌蟲2.5~6.5cm，幼蟲型，外形很像幼蟲，全體淡黃色，無後翅也無翅鞘，腹部末端有一對C字形的發光器。幼蟲體型扁平寬闊，體色為黑褐色，各節邊緣為黃褐色。在臺灣的螢火蟲中有一個物種的雄性成蟲長得有點像蟑螂，便是此種，因此也被戲稱為蟑螂螢。","屏東縣"
                )
            )
        }
    }

    fun getAllBug(){
        bugList.addSource(database.loadAllBug()){ bugs ->
            bugList.postValue(bugs)
        }
    }

    fun searchBug(name: String){
        bugList.addSource(database.findBugs(name)){ bugs ->
            bugList.postValue(bugs)
        }
    }

    fun getBug(bugId: Long) {  //get the scene data by given its id in the database
        viewModelScope.launch {
            selectedBug.value = database.loadOneBug(bugId)
        }
    }

    fun insertBug(newBug: Bug) {  //add a new scene data inti the database
        viewModelScope.launch {
            database.insertBug(newBug)
        }
    }

    fun deleteBug(oldBug: Bug) {  //add a new scene data inti the database
        viewModelScope.launch {
            database.deleteBug(oldBug)
        }
    }

    fun retrieveWeather(location: String) {
        //initially set null
        cityWeather.value = null
        viewModelScope.launch {
            try {
                val result =
                    GetService.retrofitService.getAppData(location)
                cityWeather.value = result.asTemperatureEntity()
            } catch (e: Exception) {
                Log.d("Main", "Fail to access: ${e.message}")
            }
        }
    }
}