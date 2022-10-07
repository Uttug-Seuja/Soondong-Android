package com.junjange.soondong.utils

import com.junjange.soondong.data.Match
import com.junjange.soondong.data.Player

object Constants {

    fun getMatches() : ArrayList<Match> {
        val matchesList = ArrayList<Match>()
        // 1
        val match1 = Match(
            startTime = "08:00",
            endTime = "10:00",
            place = "순천향대학교 대운동장",
            gender = 0,
            people = "11vs11",
            level = "모든레벨",
            state = 0)

        matchesList.add(match1)

        val match2 = Match(
            startTime = "10:00",
            endTime = "12:00",
            place = "순천향대학교 대운동장",
            gender = 0,
            people = "11vs11",
            level = "모든레벨",
            state = 0)

        matchesList.add(match2)

        val match3 = Match(
            startTime = "12:00",
            endTime = "14:00",
            place = "순천향대학교 대운동장",
            gender = 0,
            people = "11vs11",
            level = "모든레벨",
            state = 1)

        matchesList.add(match3)

        // 1
        val match4 = Match(
            startTime = "14:00",
            endTime = "16:00",
            place = "순천향대학교 대운동장",
            gender = 0,
            people = "11vs11",
            level = "모든레벨",
            state = 1)

        matchesList.add(match4)

        val match5 = Match(
            startTime = "16:00",
            endTime = "18:00",
            place = "순천향대학교 대운동장",
            gender = 0,
            people = "11vs11",
            level = "모든레벨",
            state = 2)

        matchesList.add(match5)

        val match6 = Match(
            startTime = "18:00",
            endTime = "20:00",
            place = "순천향대학교 대운동장",
            gender = 0,
            people = "11vs11",
            level = "모든레벨",
            state = 0)

        matchesList.add(match6)

        // 1
        val match7 = Match(
            startTime = "20:00",
            endTime = "22:00",
            place = "순천향대학교 대운동장",
            gender = 0,
            people = "11vs11",
            level = "모든레벨",
            state = 1)

        matchesList.add(match7)

        val match8 = Match(
            startTime = "22:00",
            endTime = "24:00",
            place = "순천향대학교 대운동장",
            gender = 0,
            people = "11vs11",
            level = "모든레벨",
            state = 1)

        matchesList.add(match8)


        return matchesList

    }

    fun getPlayer() : ArrayList<Player> {
        val playerList = ArrayList<Player>()
        // 1
        val player1 = Player(
            "20181566",
            "조*장"
        )

        playerList.add(player1)

        val player2 = Player(
            "20181567",
            "김*우"
        )

        playerList.add(player2)


        val player3 = Player(
            "20181568",
            "이*희"
        )

        playerList.add(player3)


        val player4 = Player(
            "20181569",
            "이*일"
        )

        playerList.add(player4)


        val player5 = Player(
            "20181570",
            "김*준"
        )

        playerList.add(player5)


        val player6 = Player(
            "20181571",
            "조*환"
        )

        playerList.add(player6)


        val player7 = Player(
            "20181572",
            "유*현"
        )

        playerList.add(player7)


        val player8 = Player(
            "20181573",
            "김*수"
        )

        playerList.add(player8)


        val player9 = Player(
            "20181574",
            "조*재"
        )

        playerList.add(player9)


        val player10 = Player(
            "20181575",
            "이*주"
        )

        playerList.add(player10)


        val player11 = Player(
            "20181576",
            "박*우"
        )

        playerList.add(player11)


        val player12 = Player(
            "20181577",
            "한*준"
        )

        playerList.add(player12)


        val player13 = Player(
            "20181578",
            "한*남"
        )

        playerList.add(player13)


        val player14 = Player(
            "20181579",
            "최*혁"
        )

        playerList.add(player14)


        val player15 = Player(
            "20181580",
            "장*석"
        )

        playerList.add(player15)


        val player16 = Player(
            "20181581",
            "하*재"
        )

        playerList.add(player16)


        val player17 = Player(
            "20181582",
            "허*재"
        )

        playerList.add(player17)


        val player18 = Player(
            "20181583",
            "신*용"
        )

        playerList.add(player18)


        val player19 = Player(
            "20181584",
            "김*용"
        )

        playerList.add(player19)


        val player20 = Player(
            "20181585",
            "송*석"
        )

        playerList.add(player20)


        val player21 = Player(
            "20181586",
            "김*지"
        )

        playerList.add(player21)


        val player22 = Player(
            "20181587",
            "이*혜"
        )

        playerList.add(player22)




        return playerList

    }


}