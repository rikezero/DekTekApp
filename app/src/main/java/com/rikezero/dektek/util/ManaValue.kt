package com.rikezero.dektek.util

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.rikezero.dektek.R.drawable.*
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class ManaValue(@DrawableRes val drawable: Int): Parcelable {
    // Numbers 1 to 20
    ZERO(ic_mana_0), ONE(ic_mana_1), TWO(ic_mana_2), THREE(ic_mana_3), FOUR(ic_mana_4), FIVE(ic_mana_5), SIX(ic_mana_6), SEVEN(ic_mana_7), EIGHT(ic_mana_8), NINE(ic_mana_9), TEN(ic_mana_10),
    ELEVEN(ic_mana_11), TWELVE(ic_mana_12), THIRTEEN(ic_mana_13), FOURTEEN(ic_mana_14), FIFTEEN(ic_mana_15), SIXTEEN(ic_mana_16), SEVENTEEN(ic_mana_17), EIGHTEEN(ic_mana_18), NINETEEN(ic_mana_19), TWENTY(ic_mana_20),

    // Single mana symbols
    X(ic_mana_x), W(ic_mana_w), G(ic_mana_g), U(ic_mana_u), B(ic_mana_b), R(ic_mana_r),

    // Mana symbols combined with P
    WP(ic_mana_wp), GP(ic_mana_gp), UP(ic_mana_up), BP(ic_mana_bp), RP(ic_mana_rp),

    // Combinations of mana symbols
    RW(ic_mana_rw), RG(ic_mana_rg), WU(ic_mana_wu), WB(ic_mana_wb), GU(ic_mana_gu), GW(ic_mana_gw), UB(ic_mana_ub), UR(ic_mana_ur), BR(ic_mana_br), BG(ic_mana_bg),

    // 2 and a mana symbol
    TWO_W(ic_mana_2w), TWO_G(ic_mana_2g), TWO_U(ic_mana_2u), TWO_B(ic_mana_2b), TWO_R(ic_mana_2r),

    // C combinations
    CW(ic_mana_cw), CG(ic_mana_2g), CU(ic_mana_cu), CB(ic_mana_2b), CR(ic_mana_2r),

    // Dual Phyrexian
    WUP(ic_mana_wup), RWP(ic_mana_rwp), GUP(ic_mana_gup), UBP(ic_mana_ubp), URP(ic_mana_urp), WBP(ic_mana_wbp), GWP(ic_mana_gwp), RGP(ic_mana_rgp), BRP(ic_mana_brp), BGP(ic_mana_bgp),

    // Snow
    S(ic_mana_inverteds);

    companion object {
        fun fromString(value: String): ManaValue {
            return when (value) {
                "0" -> ZERO
                "1" -> ONE
                "2" -> TWO
                "3" -> THREE
                "4" -> FOUR
                "5" -> FIVE
                "6" -> SIX
                "7" -> SEVEN
                "8" -> EIGHT
                "9" -> NINE
                "10" -> TEN
                "11" -> ELEVEN
                "12" -> TWELVE
                "13" -> THIRTEEN
                "14" -> FOURTEEN
                "15" -> FIFTEEN
                "16" -> SIXTEEN
                "17" -> SEVENTEEN
                "18" -> EIGHTEEN
                "19" -> NINETEEN
                "20" -> TWENTY
                "2W" -> TWO_W
                "2G" -> TWO_G
                "2U" -> TWO_U
                "2B" -> TWO_B
                "2R" -> TWO_R
                "CW" -> CW
                "CG" -> CG
                "CU" -> CU
                "CB" -> CB
                "CR" -> CR
                else -> try {
                    valueOf(value.uppercase())
                } catch (e: IllegalArgumentException) {
                    throw IllegalArgumentException("No mapped ManaValue found for input: '$value'")
                }
            }
        }
    }
}