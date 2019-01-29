head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerInformationPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>VirtualServerInformation</b>データベーステーブルで一意である1つのレコードをあらわす<b>VirtualServerInformation</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>VirtualServerInformation</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see VirtualServerInformationRow 
 */
public class VirtualServerInformationPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "virtual_server_information";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: VirtualServerInformationRow. 
   */
  public RowType getRowType() {
    return VirtualServerInformationRow.TYPE;
  }

  /**
   * <em>virtual_server_number_jsoes</em>カラムの値をあらわす7桁以下のString値 
   */
  public String virtual_server_number_jsoes;


  /** 
   * デフォルトコンストラクタ 
   */
  public VirtualServerInformationPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_virtualServerNumberJsoes <em>virtual_server_number_jsoes</em>カラムの値をあらわす7桁以下のString値 
   */
  public VirtualServerInformationPK( String p_virtualServerNumberJsoes ) {
      virtual_server_number_jsoes = p_virtualServerNumberJsoes;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static VirtualServerInformationPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new VirtualServerInformationPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return virtual_server_number_jsoes;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof VirtualServerInformationPK) )
      return false;
    VirtualServerInformationPK o = (VirtualServerInformationPK) other;
    if ( virtual_server_number_jsoes == null ) {
      if ( o.virtual_server_number_jsoes != null )
        return false;
    } else if ( !virtual_server_number_jsoes.equals( o.virtual_server_number_jsoes ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (virtual_server_number_jsoes!=null? virtual_server_number_jsoes.hashCode(): 0) 
    ;
  }

}

@
