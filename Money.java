public class Money {
    private int phpOne;
    private int phpFive;
    private int phpTen;
    private int phpTwenty;
    private int phpFifty;
    private int phpOneHundred;
    private int phpTwoHundred;
    private int phpFiveHundred;
    private int phpOneThousand;

    public Money(){
        this.phpOne = 0;
        this.phpFive = 0;
        this.phpTen = 0;
        this.phpTwenty = 0;
        this.phpFifty = 0;
        this.phpOneHundred = 0;
        this.phpTwoHundred = 0;
        this.phpFiveHundred = 0;
        this.phpOneThousand = 0;
    }
    
    public Money (int phpOne, int phpFive, int phpTen, int phpTwenty, int phpFifty, int phpOneHundred, int phpTwoHundred, int phpFiveHundred, int phpOneThousand){
        this.phpOne = phpOne;
        this.phpFive = phpFive;
        this.phpTen = phpTen;
        this.phpTwenty = phpTwenty;
        this.phpFifty = phpFifty;
        this.phpOneHundred = phpOneHundred;
        this.phpTwoHundred = phpTwoHundred;
        this.phpFiveHundred = phpFiveHundred;
        this.phpOneThousand = phpOneThousand;
    }

    public void addMoney(Money m){
        this.phpOne += m.phpOne;
        this.phpFive += m.phpFive;
        this.phpTen += m.phpTen;
        this.phpTwenty += m.phpTwenty;
        this.phpFifty += m.phpFifty;
        this.phpOneHundred += m.phpOneHundred;
        this.phpTwoHundred += m.phpTwoHundred;
        this.phpFiveHundred += m.phpFiveHundred;
        this.phpOneThousand += m.phpOneThousand;
    }

    public void subtractMoney(Money m){
        this.phpOne -= m.phpOne;
        this.phpFive -= m.phpFive;
        this.phpTen -= m.phpTen;
        this.phpTwenty -= m.phpTwenty;
        this.phpFifty -= m.phpFifty;
        this.phpOneHundred -= m.phpOneHundred;
        this.phpTwoHundred -= m.phpTwoHundred;
        this.phpFiveHundred -= m.phpFiveHundred;
        this.phpOneThousand -= m.phpOneThousand;
    }

    public int totalMoney(){
        return (phpOne)+(phpFive*5)+(phpTen*10)+(phpTwenty*20)+(phpFifty*50)+(phpOneHundred*100)+(phpTwoHundred*200)+(phpFiveHundred*500)+(phpOneThousand*1000);
    }

    public int getPhpOne(){
        return phpOne;
    }

    public int getPhpFive(){
        return phpFive;
    }

    public int getPhpTen(){
        return phpTen;
    }

    public int getPhpTwenty(){
        return phpTwenty;
    }

    public int getPhpFifty(){
        return phpFifty;
    }

    public int getPhpOneHundred(){
        return phpOneHundred;
    }

    public int getPhpTwoHundred(){
        return phpTwoHundred;
    }

    public int getPhpFiveHundred(){
        return phpFiveHundred;
    }

    public int getPhpOneThousand(){
        return phpOneThousand;
    } 

    public void setPhpOne(int phpOne){
        this.phpOne = phpOne;
    }

    public void setPhpFive(int phpFive){
        this.phpFive = phpFive;
    }

    public void setPhpTen(int phpTen){
        this.phpTen = phpTen;
    }

    public void setPhpTwenty(int phpTwenty){
        this.phpTwenty = phpTwenty;
    }

    public void setPhpFifty(int phpFifty){
        this.phpFifty = phpFifty;
    }

    public void setPhpOneHundred(int phpOneHundred){
        this.phpOneHundred = phpOneHundred;
    }

    public void setPhpTwoHundred(int phpTwoHundred){
        this.phpTwoHundred = phpTwoHundred;
    }

    public void setPhpFiveHundred(int phpFiveHundred){
        this.phpFiveHundred = phpFiveHundred;
    }

    public void setPhpOneThousand(int phpOneThousand){
        this.phpOneThousand = phpOneThousand;
    }

    public int calculateTotalAmount() {
        return (phpOne * 1) + (phpFive * 5) + (phpTen * 10) + (phpTwenty * 20) + (phpFifty * 50) + (phpOneHundred * 100) + (phpTwoHundred * 200) + (phpFiveHundred * 500) + (phpOneThousand * 1000);
    }
}

