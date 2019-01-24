/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteMessageクラス(DOTQuoteMessage.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;



/**
 * (時価メッセージ)<BR>
 * <BR>
 * 時価サーバから配信される時価情報を含むメッセージ。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteMessage
{
    
    /** シーケンスNO */
    long sequenceNo;
    
    /** 更新時間 */
    long updatedTime;
    
    /** レコード数 */
    int count;
    
    /** 時価情報 */
    byte[] data;
    
    /**
     * コンストラクタ
     */
    DOTQuoteMessage()
    {
        data = new byte[DOTQuoteConstants.MAX_DATA_SIZE];
    }
    
    /**
     * コンストラクタ
     * 
     * @param count レコード数
     * @param data 時価情報
     * @deprecated
     */
    DOTQuoteMessage(int count, byte[] data)
    {
        this.count = count;
        this.data = data;
    }
    
    /**
     * (copy)<BR>
     * <BR>
     * 指定した時価メッセージからこの時価メッセージに内容をコピーする。<BR>
     * 
     * @param src コピー元時価メッセージ
     */
    void copy(DOTQuoteMessage src)
    {
        if (src != null)
        {
            this.sequenceNo = src.sequenceNo;
            this.updatedTime = src.updatedTime;
            this.count = src.count;
            System.arraycopy(
                src.data, 
                0, 
                this.data, 
                0, 
                src.count * DOTQuoteConstants.RECORD_SIZE);
        }
    }
    
    /**
     * (parse)<BR>
     * <BR>
     * パラメータで指定した時価イベントImplにこの時価メッセージが
     * 持つ時価情報を設定する。<BR>
     * 
     * @param events 時価情報を設定する時価イベントImpl
     * @return 時価イベントImplに設定された時価情報の件数
     */
    int parse(DOTQuoteEventImpl[] events)
    {
        int validDataCnt = 0;
        int offset = 0;
        for (int i = 0; i < count; i++, offset += DOTQuoteConstants.RECORD_SIZE)
        {
            
            // 時価サーバから配信されたシーケンスNOに枝番をつける
            long l_lngSequenceNo = (sequenceNo * 100) + i + 1;
            
            // 時価情報の各フィールドに値を設定する。
            if (events[i].setData(
                data, offset, 
                DOTQuoteConstants.RECORD_SIZE, 
                l_lngSequenceNo, updatedTime))
            {
                validDataCnt++;
            }
        }
        return validDataCnt;
    }

}
