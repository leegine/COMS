head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�������Ɖ���̓��X�|���X (WEB3AdminORFutOpOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�敨OP�������Ɖ���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�敨OP�������Ɖ���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_fut_op_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (�������ꗗ)<BR>
     * <BR>
     * �������̔z��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * An array of orderBizDate<BR>
     * <BR>
     */
    public Date[] orderBizDateList;

    /**
     * (�w����ʈꗗ)<BR>
     * <BR>
     * �w����ʂ̔z��<BR>
     * <BR>
     * An array of targetProduct<BR>
     * <BR>
     */
    public String[] targetProductList;

    /**
     * (���s�����ꗗ)<BR>
     * <BR>
     * ���s�����̔z��<BR>
     * <BR>
     * An array of execCond<BR>
     * <BR>
     */
    public String[] execCondList = null;

    /**
     * (���������敪�ꗗ)<BR>
     * <BR>
     * ���������敪�̔z��<BR>
     * <BR>
     * An array of expirationDateType<BR>
     * <BR>
     */
    public String[] expirationDateTypeList = null;

    /**
     * (���������ꗗ)<BR>
     * <BR>
     * ���������̔z��<BR>
     * <BR>
     * An array of orderCondType<BR>
     * <BR>
     */
    public String[] orderCondTypeList = null;

	/**
	 * (�����o�H�ꗗ)<BR>
	 * <BR>
	 * �����o�H�ꗗ�̔z��<BR>
	 * <BR>
	 */
	public String[] orderRootList = null;

    /**
     * �i�戵���i�ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;

    /**
     * @@roseuid 4212FBAB0111
     */
    public WEB3AdminORFutOpOrderExecutionRefInputResponse()
    {

    }

    /**
     * @@param l_request l_request
     * @@roseuid 41FD94070000
     */
    public WEB3AdminORFutOpOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
