head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcLockPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>IfoDepositCalcLock</b>データベーステーブルで一意である1つのレコードをあらわす<b>IfoDepositCalcLock</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>IfoDepositCalcLock</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDepositCalcLockRow 
 */
public class IfoDepositCalcLockPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_deposit_calc_lock";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: IfoDepositCalcLockRow. 
   */
  public RowType getRowType() {
    return IfoDepositCalcLockRow.TYPE;
  }

  /**
   * <em>service_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public String service_name;
  /**
   * <em>thread_no</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long thread_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public IfoDepositCalcLockPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_serviceName <em>service_name</em>カラムの値をあらわす100桁以下のString値 
   * @@param p_threadNo <em>thread_no</em>カラムの値をあらわす18桁以下のlong値 
   */
  public IfoDepositCalcLockPK( String p_serviceName, long p_threadNo ) {
      service_name = p_serviceName;
      thread_no = p_threadNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static IfoDepositCalcLockPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoDepositCalcLockPK pk = new IfoDepositCalcLockPK();
    int i = pkValueString.indexOf(',');
    pk.service_name = pkValueString.substring(0,i);
    pk.thread_no = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = service_name + "," + String.valueOf(thread_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoDepositCalcLockPK) )
      return false;
    IfoDepositCalcLockPK o = (IfoDepositCalcLockPK) other;
    if ( service_name == null ) {
      if ( o.service_name != null )
        return false;
    } else if ( !service_name.equals( o.service_name ) ) {
        return false;
    }
    if ( thread_no != o.thread_no )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (service_name!=null? service_name.hashCode(): 0) 
        + ((int) thread_no)
    ;
  }

}

@
