head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.39.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ParticipantPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>Participant</b>データベーステーブルで一意である1つのレコードをあらわす<b>Participant</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>Participant</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ParticipantRow 
 */
public class ParticipantPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "participant";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: ParticipantRow. 
   */
  public RowType getRowType() {
    return ParticipantRow.TYPE;
  }

  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;
  /**
   * <em>participant_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long participant_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public ParticipantPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_participantId <em>participant_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public ParticipantPK( long p_accountId, long p_participantId ) {
      account_id = p_accountId;
      participant_id = p_participantId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static ParticipantPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    ParticipantPK pk = new ParticipantPK();
    int i = pkValueString.indexOf(',');
    pk.account_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.participant_id = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + String.valueOf(participant_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof ParticipantPK) )
      return false;
    ParticipantPK o = (ParticipantPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( participant_id != o.participant_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) account_id)
        + ((int) participant_id)
    ;
  }

}

@
