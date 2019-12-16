package com.mahjong.dto

/*
    麻雀マネージャーの表示用データを保持するDTO
 */
class MahjongManagerDto(
    _playerName: String,
    _totalScore: Double,
    _rankingRate: Double,
    _playNum: Int,
    _firstRate: Int,
    _secondRate: Int,
    _thirdRate: Int,
    _fourthRate: Int,
    _minusRate: Double,
    _averagePoint: Int,
    _maxPoint: Int
) {

    //対局者名
    val playerName: String

    //成績
    val totalScore: Double

    //順位率
    val rankingRate: Double

    //対局数
    val playNum: Int

    //1位率
    val firstRate: Int

    //2位率
    val secondRate: Int

    //3位率
    val thirdRate: Int

    //4位率
    val fourthRate: Int

    //飛び回数
    val minusRate: Double

    //平均得点
    val averagePoint: Int

    //最大得点
    val maxPoint: Int

    //コンストラクタで各メンバを初期化
    init {

        //対局者名
        playerName = _playerName

        //成績
        totalScore = _totalScore

        //順位率
        rankingRate = _rankingRate

        //対局数
        playNum = _playNum

        //1位率
        firstRate = _firstRate

        //2位率
        secondRate = _secondRate

        //3位率
        thirdRate = _thirdRate

        //4位率
        fourthRate = _fourthRate

        //飛び回数
        minusRate = _minusRate

        //平均得点
        averagePoint = _averagePoint

        //最大得点
        maxPoint = _maxPoint

    }


}