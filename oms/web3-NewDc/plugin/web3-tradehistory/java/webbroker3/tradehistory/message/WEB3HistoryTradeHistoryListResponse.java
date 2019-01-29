head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ꗗ���X�|���X(WEB3HistoryTradeHistoryListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  �� �� �@@(���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (��������ꗗ���X�|���X)<BR>
 * ��������ꗗ���X�|���X�N���X<BR>
 * 
 * @@author �� �� �@@
 * @@version 1.0 
 */
public class WEB3HistoryTradeHistoryListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeHistoryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221710L;
         
    /**
     * (����񍐏����{�t���O)<BR>
     * ����񍐏����{�t���O<BR>
     * <BR>
     * false: �����{<BR>
     * true:�@@���{<BR>
     */
    public boolean tradingReportFlag;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (��n���ʎc�����ꗗ)<BR>
     * ��n���ʎc�����ꗗ<BR> 
     */
    public WEB3HistoryDailyBalanceUnit[] dailyBalanceUnits;
    
    /**
     * @@roseuid 41789C4B03A9
     */
    public WEB3HistoryTradeHistoryListResponse() 
    {
     
    }
    
    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3HistoryTradeHistoryListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
