import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Paweł Zabczyński on 12/05/2017.
 *
 * <p>Class to convert and extract data from hex intel object file Full documentation of this format
 * is on: http://microsym.com/editor/assets/intelhex.pdf
 */
public class IntelHex {
  private String oldPath;
  private String newPath;
  private LinkedList<String> oldList = new LinkedList<>();
  private LinkedList<String> newList = new LinkedList<>();

  /**
   * TO DO: -cntRecords
   * -getStartCode <-------done
   * -getByteCount <-------done
   * -getAddress <-------done
   * -getRecordType <-------done
   * -getData <-------done
   */

  /*constructors*/

  IntelHex() {}

  IntelHex(String path) {
    this.oldPath = path;
    oldList = fileToLinkedList(path);
  }

  IntelHex(String pathOld, String pathNew) {
    this.oldPath = pathOld;
    this.newPath = pathNew;

    oldList = fileToLinkedList(pathOld);
    newList = fileToLinkedList(pathNew);
  }

  /*public methods*/
  /** compareHexFile return only changed records in file */
  public LinkedList<String> compareHexFile() {
    LinkedList<String> list = new LinkedList<>();
    LinkedList<String> retList = new LinkedList<>();
    LinkedList<Integer> buffList = new LinkedList<>();

    for (String s : this.newList) {
      retList.add(s);
    }

    int cnt;

    for (String s : this.oldList) {
      cnt = 0;

      for (String s1 : this.newList) {
        cnt++;

        if (s1.equals(s)) {
          list.add(s1);
        }
      }
    }

    for (String s : list) {
      cnt = 0;
      for (String s1 : this.newList) {

        if (s.equals(s1)) {
          buffList.add(cnt);
        }
        cnt++;
      }
    }
    cnt = 0;

    for (Integer x : buffList) {
      retList.remove(x - cnt);
      cnt++;
    }

    return retList;
  }

  /*Private methods*/

  private LinkedList<String> fileToLinkedList(String path) {
    LinkedList<String> list = new LinkedList<>();
    File file = new File(path);

    try {
      if (file.exists() && file.isFile()) {
        Scanner fileScaner = new Scanner(file);
        while (fileScaner.hasNext()) {
          list.add(fileScaner.next());
        }
      }

    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }

    return list;
  }

  private int hexToInt(String s) {
    int nInt = 0;
    int buffInt;
    int cnt = 0;
    char buffCH;

    while (((s.length() - 1) - cnt) != -1) {
      buffCH = s.charAt((s.length() - 1) - cnt);
      if ((int) buffCH >= (int) '0' && (int) buffCH <= (int) '9') {
        buffInt = (int) buffCH - (int) '0';
        nInt += buffInt * (int) Math.pow(16, cnt);
      } else {
        if ((int) buffCH >= (int) 'A' && (int) buffCH <= (int) 'F') {
          buffInt = 10 + ((int) buffCH - (int) 'A');

          nInt += buffInt * (int) Math.pow(16, cnt);
        }
      }
      cnt++;
    }
    return nInt;
  }

  /**
   * getStartCode return first byte(one char in ASCI) of block of data(named record as well) in
   * intel hex object format file. This bite must contain colon ":". If it dasn't contain colon
   * return null value.
   */
  private String getStartCode(String s) {
    s = "" + s.charAt(0);
    if (s.equals(":")) return s;
    else return null;
  }
  /**
   * getByteCount(Reclen field in the record) return. This field contain number bytes of data in the
   * record. Reclen field has 2 bytes(4 haracters in hex)
   */
  private String getByteCount(String s) {
    String buffS = "";
    for (int i = 0; i < 2; i++) {
      buffS += s.charAt(i + 1);
    }
    return buffS;
  }

  /**
   * getAddres return address in the memory, type of addres. Is used only in records contain data.
   * It's mean olly when rectype is 00.
   */
  private String getAddress(String s) {
    String buffS = "";

    for (int i = 0; i < 2; i++) {
      buffS += s.charAt(i + 3);
    }

    return buffS;
  }

  /**
   * getRecordType return 1B(2 character) contain type of record - '00' (0h00, dec: 0, bin: 0000
   * 0000) record contain data and 16 bit address - '01' (0h01, dec: 1, bin: 0000 0001) end of file
   * record - '02' (0h02, dec: 2, bin: 0000 0010) Extended segment address record - '03' (0h03, dec:
   * 3, bin: 0000 0011) - '04' (0h04, dec: 4, bin: 0000 0100) - '05' (0h05, dec: 5, bin: 0000 0101)
   */
  private String getRecordType(String s) {
    String buffS = "";

    for (int i = 0; i < 2; i++) {
      buffS += s.charAt(i + 5);
    }

    return buffS;
  }

  private String getData(String s, int n) {
    String buffS = "";

    for (int i = 0; i < n; i++) {
      buffS += s.charAt(i + 7);
    }
    return buffS;
  }

  private String getCheckCode(String s, int n) {
    String buffS = "";

    for (int i = 0; i < n; i++) {
      buffS += s.charAt(i + 7);
    }
    return buffS;
  }

  /*Setters*/
  public void setOldPath(String path) {
    this.oldPath = path;
  }

  public void setNewPath(String path) {
    this.newPath = path;
  }

  /*Getters*/
  public String getOldPath() {
    return this.oldPath;
  }
}
