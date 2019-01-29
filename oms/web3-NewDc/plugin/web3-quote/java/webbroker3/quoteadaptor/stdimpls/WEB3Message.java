head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3Message.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls;

/**
 * WEB3MessageQueueにプットされるクラス<BR>
 * <BR>
 * このクラスは、時価サーバより配信された時価情報をバイト配列として
 * 保持するクラスである。
 * このクラスのひとつのインスタンスは、時価サーバより一度に配信された
 * 時価情報を保持している。
 * 時価サーバが、一度にn件の時価レコードを配信した場合、このクラスのインスタンスは、
 * n件の時価レコードをバイト配列として保持する。
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3Message
{
    int count; // =0
    byte[] data;

    /**
     * コンストラクタ
     */
    WEB3Message()
    {
        data = new byte[WEB3QuoteConstants.MAX_DATA_SIZE];
    }

    /**
     * コンストラクタ
     * 
     * @@param c 時価レコード数
     * @@param r 時価情報を保持するバイト配列
     */
    WEB3Message(int c, byte[] r)
    {
        this.count = c;
        this.data = r;
    }

    /**
     * 指定したWEB3Messageから時価情報をコピーする
     * 
     * @@param src コピー元時価情報
     */
    void copy(WEB3Message src)
    {
        if (src != null)
        {
            this.count = src.count;
            System.arraycopy(
                src.data,
                0,
                this.data,
                0,
                src.count * WEB3QuoteConstants.RECORD_SIZE);
        }
    }

    /**
     * 現在保持している時価情報をパースし、
     * 指定したWEB3QuoteEventImplに時価情報を設定する。
     * 
     * @@param holders 時価情報を設定するWEB3QuoteEventImpl
     * @@return 設定された時価情報のレコード数
     */
    int parse(WEB3QuoteEventImpl[] holders)
    {
        if (holders.length < count)
        {
            throw new Error("Too few holders to parse Message");
        }

        int index = 0;
        int valid = 0;
        for (int i = 0;
            i < count;
            i++, index += WEB3QuoteConstants.RECORD_SIZE)
        {
            if (holders[valid]
                .setData(data, index, WEB3QuoteConstants.RECORD_SIZE))
            {
                valid++;
            }

        }

        return valid;
    }
}
@
