head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���M�ݓ��������Ɖ���̓��X�|���X(WEB3AdminORMutualRuitoOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE���M�ݓ��������Ɖ���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE���M�ݓ��������Ɖ���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_mutual_ruito_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (�������ꗗ)<BR>
     * <BR>
     * �������̔z��<BR>
     * <BR>
     * An array of orderBizDate<BR>
     * <BR>
     */
    public Date[] orderBizDateList;

    /**
     * (�����o�H�敪�ꗗ)<BR>
     * <BR>
     * �����o�H�敪�̈ꗗ<BR>
     */
    public String[] orderRootList = null;
    
    /**
     * �i�戵���i�ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;

    /**
     * �i�����ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminORProductNameSetUnit[] productNameSetList;

    /**
     * @@roseuid 4212FC140259
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
