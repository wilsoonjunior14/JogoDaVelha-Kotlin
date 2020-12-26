package wilson.application.databindingtest.viewModels

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.library.baseAdapters.BR
import wilson.application.databindingtest.R
import wilson.application.databindingtest.models.User

class GameViewModel(view: View) : BaseObservable(){

    var player1 = User("Fco Wilson")
    private var view = view
    private var isOnePlayer = true
    private var existsWinner = false
    private var playerWinner: Int = 0
    private lateinit var gameArray: Array<Array<Int>>
    private var buttonsArray = arrayOf(R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04, R.id.btn_05, R.id.btn_06, R.id.btn_07, R.id.btn_08, R.id.btn_09)

    init {
        resetGame()
    }

    fun tapOnImageView(view: View, x: Int, y: Int){

        if (!this.existsWinner && this.gameArray.get(x).get(y) == -1){
            if (this.isOnePlayer){
                view.background = view.resources.getDrawable(R.drawable.x)
            }else {
                view.background = view.resources.getDrawable(R.drawable.o)
            }
            this.gameArray.get(x).set(y, if (this.isOnePlayer) 1 else 2)
            this.isOnePlayer = !this.isOnePlayer

            playerWinner = if (checksIfPlayerWins(1)) 1 else playerWinner
            playerWinner = if (checksIfPlayerWins(2)) 2 else playerWinner

            if (playerWinner != 0){
                existsWinner = true
            }
        }

        System.out.println("Player 1 wins: "+checksIfPlayerWins(1))
        System.out.println("Player 2 wins: "+checksIfPlayerWins(2))

        this.showsMessageIfExistsWinner()
    }

    fun checksIfPlayerWins(numberOfPlayer: Int) : Boolean{

        val firstRow = this.gameArray.get(0)
        val secondRow = this.gameArray.get(1)
        val thirdRow = this.gameArray.get(2)

        if (firstRow.get(0) == numberOfPlayer && firstRow.get(1) == numberOfPlayer && firstRow.get(2) == numberOfPlayer){
            return true
        }

        if (firstRow.get(0) == numberOfPlayer && secondRow.get(0) == numberOfPlayer && thirdRow.get(0) == numberOfPlayer){
            return true
        }

        if (firstRow.get(0) == numberOfPlayer && secondRow.get(1) == numberOfPlayer && thirdRow.get(2) == numberOfPlayer){
            return true
        }

        if (firstRow.get(1) == numberOfPlayer && secondRow.get(1) == numberOfPlayer && thirdRow.get(1) == numberOfPlayer){
            return true
        }

        if (firstRow.get(2) == numberOfPlayer && secondRow.get(2) == numberOfPlayer && thirdRow.get(2) == numberOfPlayer){
            return true
        }

        if (firstRow.get(1) == numberOfPlayer && secondRow.get(1) == numberOfPlayer && thirdRow.get(1) == numberOfPlayer){
            return true
        }

        if (firstRow.get(2) == numberOfPlayer && secondRow.get(2) == numberOfPlayer && thirdRow.get(2) == numberOfPlayer){
            return true
        }

        if (firstRow.get(2) == numberOfPlayer && secondRow.get(1) == numberOfPlayer && thirdRow.get(0) == numberOfPlayer){
            return true
        }

        return false
    }

    fun resetGame(){
        this.isOnePlayer = true
        this.existsWinner = false
        this.playerWinner = 0
        this.gameArray = arrayOf(arrayOf(-1,-1,-1), arrayOf(-1,-1,-1), arrayOf(-1,-1,-1))

        for (id in this.buttonsArray) {
            this.view.findViewById<ImageView>(id).background = null
        }

        Toast.makeText(this.view.context, "The game was initialized", Toast.LENGTH_SHORT).show()
    }

    fun showsMessageIfExistsWinner(){
        if (existsWinner){
            Toast.makeText(view.context, "The player "+playerWinner+" already win the game", Toast.LENGTH_SHORT).show()
            return
        }
    }
}