import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
//BrowserImplementClass is implement class of Browser
interface Browser{
    public void openNewTab();
    public void rearranging(char option);
    public String backButton();
    public String forwardButton();
    public void mostVisitedWebsite();
    public  void leastVisitedWebsite();
    public void history();
    public void closeTheTab();
    public String openLastClosedTab();
    public void closeTheBrower();
}

//This class is used to implement the browser interface.

class BrowserImplementClass implements Browser{
    //This map is used count the no of visits for a website.
    static Map<String,Integer> mostVisited =new HashMap<>();
    // it is used for the history of the tabs opened.
    static ArrayList<String> history=new ArrayList<>();
    //storing for closed tabs.
    Stack<String> closeTab= new Stack<>();
    //count the noOf tabs available
    static int countTab=0;
    //current tab open index
    static int currentTab=-1;
    //storing the all tabs 
    private List<String> list;
   
    
    Rearranging rearr;
    Scanner scanner =new Scanner(System.in);
    BrowserImplementClass() {
        //initially linkedlist creating
        list=new LinkedList<String>();
    }
  //openNewTab used for noOf tabs storing in linkedList
    public void openNewTab(){
        String tabName=scanner.next();
        countTab++;
        currentTab++; 
        mostVisited.put(tabName,mostVisited.getOrDefault(tabName,0)+1);
        history.add(tabName);
        System.out.println(tabName+" is rendering....");
          
        this.list.add(tabName); 
            
        System.out.println("Good To Go..");
       
    }

    //rearraning method used for display reverse , middle tab, what tabs available in linkedlist
    public void rearranging(char option){
        rearr=new Rearranging(list);
        
        switch(option){
            
            case 'D' :rearr.displayAll();
                    break;
            case 'R' :rearr.displayReverse();
                    break;
            case 'M' :System.out.println(rearr.middleTab());
                    break;    
            default : System.out.println("enter the correct Letter ");      
         }
        
    }
    //backButton method used for return the back tab from current tab if not return no previous tab
    public String backButton(){
        if( currentTab > 0){
        mostVisited.put(list.get(currentTab-1),mostVisited.getOrDefault(list.get(currentTab-1),0)+1);
        history.add(list.get(currentTab-1));
       
        return list.get(--currentTab);
     }
      else return "no previous tab";
    }
    //forward method used for return the forward tab from current tab if not return noforward tab
    public String forwardButton(){
       if(currentTab >= 0 && currentTab < countTab-1){
        mostVisited.put(list.get(currentTab-1),mostVisited.getOrDefault(list.get(currentTab-1),0)+1);
        history.add(list.get(currentTab+1)); 
       
        return list.get(++currentTab);
       }
       else return "no Forward Tab";
    }
    //mostVisitedWebsite method used for most visited tab
     public void mostVisitedWebsite(){
      int max=Integer.MIN_VALUE;
      String mostVisitedTab="";
   
       for(Map.Entry<String,Integer> entry : mostVisited.entrySet()){
          if(entry.getValue() > max){
            max=entry.getValue();
            mostVisitedTab=entry.getKey();
          }
       }
       System.out.println(mostVisitedTab);
    }
    //leastVisitedWebsite method used for least visited tab
    public void leastVisitedWebsite(){
        int min=Integer.MAX_VALUE;
        String leastVisitedTab="";
     
         for(Map.Entry<String,Integer> entry : mostVisited.entrySet()){
            if(entry.getValue() < min){
              min=entry.getValue();
              leastVisitedTab=entry.getKey();
            }
         }
         System.out.println(leastVisitedTab);
    }
    //history method used for history what you open the tabs
    public void history(){
        System.out.println("History..");
       for(int index=0; index < history.size();index++)
          System.out.println(history.get(index));
       return;
    }
    //closeTheTab method used for remove the current tab otherwise currently no tabs availble
    public void closeTheTab(){
      if(currentTab > 0 || !closeTab.isEmpty()){
        history.add(list.get(currentTab));
        closeTab.push(list.get(currentTab));
        list.remove(currentTab);
        
        --countTab;
       System.out.println(closeTab.peek() +" Succefully removed...");
      }
      else System.out.println("currently no tabs availble");

    }
    //openLastClosedTab method used for return last  tab closed otherwise neither tabs was created nor no tabs closed
    public String openLastClosedTab(){
        if(closeTab.size() > 0){
            history.add(closeTab.get(closeTab.size()-1));
            return closeTab.get(closeTab.size()-1);
        }
         
        return "neither tabs was created nor no tabs closed";
    }
    //closeTheBrowser method used for closed the browser
    public void closeTheBrower(){
        for(int index=list.size()-1;index >= 0;index--){
            closeTab.push(list.remove(index));
        }
        System.out.println("Browser is closed");
        }
    
}
// this class used for main method create Instance of BrowserImplementClass
public class Snist_Brower {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        boolean flag=false;
        BrowserImplementClass objBr= new BrowserImplementClass();
        while (true) { 
            
        System.out.println();
        System.out.println("Select options 1 to 10 ");
        System.out.println("1 :enter the noOF tab ");
        System.out.println("2 :enter the character only R ,D , M");
        System.out.println(" -M  is middle tab");
        System.out.println(" -R  is reverse all tabs");
        System.out.println(" -D  is display all tab");
        System.out.println("3 :back Button");
        System.out.println("4 :forward Button");
        System.out.println("5 : most visited website");
        System.out.println("6 : least visited website");
        System.out.println("7 :history");
        System.out.println("8 : close the tab");
        System.out.println("9 : open the last closed tab");
        System.out.println("10 : close the broswer");
        System.out.println();
        int option=scanner.nextInt();
        //select option 1 to 10 for further process
        if(option > 0 && option < 11){
            switch(option){
                //
                case 1: System.out.println("enter the noOf tab and  websites names ");
                        int noOfTab=scanner.nextInt();

                        for(int index=0;index<noOfTab;index++){
                            
                            objBr.openNewTab();
                        
                        }
                        break;
                case 2 :
                        
                        
                        char ch=scanner.next().charAt(0);
                        objBr.rearranging(ch);
                        
                        break;
                case 3 : 
                        System.out.println(objBr.backButton());
                        break;
                case 4 :  
                        System.out.println(objBr.forwardButton());
                        break;
                case 5 : objBr.mostVisitedWebsite();
                    break;
                case 6 :objBr.leastVisitedWebsite();
                   break;
                case 7 : objBr.history();
                    break;
                    case 8: objBr.closeTheTab();
                    break;
                case 9 : System.out.println(objBr.openLastClosedTab());
                break;
                case 10 : objBr.closeTheBrower();
                        flag=true;
                        break;
                }
                
            }
            else System.out.println("enter the correct options");
            if(flag)break;
            
            }
}
}
//this class used for option 2 such as middle Tab ,display Reverse ,display all tabs currently store in linkedlist
class Rearranging{
    private List<String> list;
    private int count;
    private int currentTab;
//construct calling from BrowserImplementClass class
    Rearranging(List<String> list){
        this.list=new LinkedList<>();
        this.list.addAll(list);
       
    }
    //middleTab
   String middleTab(){
     count=BrowserImplementClass.countTab;
     currentTab=BrowserImplementClass.currentTab;
      if(count==0) return "no Tabs";
      if(count==1) return list.get(0);
      int middle;
      if(count %2 ==0)
        middle=count/2 -1;
      else middle=count/2;
      BrowserImplementClass.history.add(list.get(middle));
      BrowserImplementClass.mostVisited.put(list.get(currentTab-1),BrowserImplementClass.mostVisited.getOrDefault(list.get(currentTab-1),0)+1);
      System.out.println(list);
      return list.get(middle);
    }
    //display reverse
   void displayReverse(){
     count=BrowserImplementClass.countTab;
     int revCount=count;
     System.out.println(list);
     System.out.println("reverse tabs..");
     while(--revCount >= 0){
        BrowserImplementClass.history.add(list.get(revCount));
        BrowserImplementClass.mostVisited.put(list.get(revCount),BrowserImplementClass.mostVisited.getOrDefault(list.get(revCount),0)+1);
        System.out.print(list.get(revCount) + " ");
     }
     System.out.println();
    }
    //display all tabs
   void displayAll(){
       count=BrowserImplementClass.countTab;
       int countAll=0;
       System.out.println("display All Tabs");
      
       while(countAll < count){
        BrowserImplementClass.history.add(list.get(countAll));
        BrowserImplementClass.mostVisited.put(list.get(countAll),BrowserImplementClass.mostVisited.getOrDefault(list.get(countAll),0)+1);
      
        System.out.print(list.get(countAll++) + " ");
       }
       System.out.println();
    }
}