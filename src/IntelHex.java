/**
 *
 * Created by Paweł Zabczyński on 12/05/2017.
 *
 */
public class IntelHex {
    private String oldPath;
    private String newPath;
    private int[] quantityRecords = new int[2];


    /**
     * TO DO:
     * -cntRecords
     * -getStartCode   <-------done
     * -getByteCount   <-------done
     * -getAddress     <-------done
     * -getRecordType  <-------done
     * -getData        <-------done
     * */

    /*public methods*/






    /*Private methods*/

    private String getStartCode(String s){
            s = ""+s.charAt(0);
            if(s.equals(":"))return s;else return null;

    }

    private String getByteCount(String s){
        String buffS = "";
        for(int i = 0; i < 2; i++){
            buffS += s.charAt(i+1);
        }
        return buffS;
    }

    private String getAddress(String s){
        String buffS = "";

        for(int i = 0; i < 2; i++){
            buffS += s.charAt(i+3);
        }

        return buffS;
    }

    private String getRecordType(String s){
        String buffS = "";

        for(int i = 0; i < 2; i++){
            buffS += s.charAt(i+5);
        }

        return buffS;
    }

    private String getData(String s, int n){
        String buffS = "";

        for(int i=0; i < n; i++){
            buffS += s.charAt(i+7);
        }
        return buffS;
    }

    private String getCheckCode(String s, int n){
        String buffS = "";

        for(int i=0; i < n; i++){
            buffS += s.charAt(i+7);
        }
        return buffS;    }

//    private int cntRecords(String namePath){
//
//        int cnt = 0;
//
//        File oldFile = new File(namePath);
//        BufferedReader oldReader = null;
//        String text = null;
//
//        try{
//            oldReader = new BufferedReader(new FileReader(oldFile));
//            while( (text = oldReader.readLine()) != null){
//                cnt++;
//            }
//
//        }catch (FileNotFoundException ex){
//
//
//        }catch (IOException ex){
//
//        }
//        finally {
//            try{
//                if(oldReader != null){
//                    oldReader.close();
//                }
//            }catch (IOException ex){
//
//            }
//        }
//        return cnt;
//    }


/*Setters*/
    public void setOldPath(String path){
        this.oldPath = path;
    }
    public void setNewPath(String path){
        this.newPath = path;
    }

/*Eetters*/
    public String getOldPath(){
        return this.oldPath;
    }

}
