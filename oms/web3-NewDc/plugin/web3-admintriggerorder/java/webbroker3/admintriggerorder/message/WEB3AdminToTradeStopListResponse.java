head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�ꗗ���X�|���X(WEB3AdminToTradeStopListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���q��(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�ꗗ���X�|���X)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�ꗗ���X�|���X�N���X<BR>
 * 
 * @@author ���q��
 * @@version 1.0 
 */
public class WEB3AdminToTradeStopListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_list";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (���ݎ���)<BR>
     * ���ݎ���<BR>
     */
    public Date currentTime;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages = "0";
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords = "0";
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex = "0";
    
    /**
     * (�戵��~���ꗗ)<BR>
     * �戵��~���ꗗ<BR>
     */
    public WEB3AdminToTradeStopInfoUnit[] tradeStopInfoList;
    
    /**
     * @@roseuid 4430D2C1009C
     */
    public WEB3AdminToTradeStopListResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminToTradeStopListResponse(WEB3AdminToTradeStopListRequest l_request)
    {
        super(l_request);
    } 
}@
