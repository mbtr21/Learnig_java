public class OutputHandel {
    private int response;


    public void setResponse(int response) {
        this.response = response;
    }


    public int getResponse() {
        return response;
    }


    public void printMessage() {
        if(this.response == 1) {
            System.out.println("You have successfully Done your Job!");
        }
        else{
            System.out.println("The Operation was failed");
        }
    }
}
