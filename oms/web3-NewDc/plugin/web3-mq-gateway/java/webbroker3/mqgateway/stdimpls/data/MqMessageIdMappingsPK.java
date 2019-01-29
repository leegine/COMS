head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.46.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	MqMessageIdMappingsPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mqgateway.stdimpls.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>MqMessageIdMappings</b>データベーステーブルで一意である1つのレコードをあらわす<b>MqMessageIdMappings</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MqMessageIdMappings</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MqMessageIdMappingsRow 
 */
public class MqMessageIdMappingsPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mq_message_id_mappings";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MqMessageIdMappingsRow. 
   */
  public RowType getRowType() {
    return MqMessageIdMappingsRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>data_code</em>カラムの値をあらわす6桁以下のString値 
   */
  public String data_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public MqMessageIdMappingsPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_dataCode <em>data_code</em>カラムの値をあらわす6桁以下のString値 
   */
  public MqMessageIdMappingsPK( String p_institutionCode, String p_dataCode ) {
      institution_code = p_institutionCode;
      data_code = p_dataCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MqMessageIdMappingsPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MqMessageIdMappingsPK pk = new MqMessageIdMappingsPK();
    int i = pkValueString.indexOf(',');
    pk.institution_code = pkValueString.substring(0,i);
    pk.data_code = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + data_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MqMessageIdMappingsPK) )
      return false;
    MqMessageIdMappingsPK o = (MqMessageIdMappingsPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( data_code == null ) {
      if ( o.data_code != null )
        return false;
    } else if ( !data_code.equals( o.data_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (data_code!=null? data_code.hashCode(): 0) 
    ;
  }

}

@
