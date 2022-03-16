interface ConnectFourRules
{
    public boolean checkVertical(char[][] b);
    public boolean checkHorizontal(char[][] b);
    public boolean checkDIAGdown(char[][] b);
    public boolean checkDIAGup(char[][] b);
}