head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���X�|���X (WEB3AdminPMAccProductTradeStopListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �������F(SRA) �V�K�쐬
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���X�|���X�j<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopListResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
	public final static String PTYPE = "admin_p_m_acc_product_trade_stop_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i���y�[�W���j<BR>
     * <BR>
     * ���y�[�W��<BR>
     * <BR>
     * totalPages<BR>
     * <BR>
     */
    public String totalPages;

    /**
     * �i�����R�[�h���j<BR>
     * <BR>
     * �����R�[�h��<BR>
     * <BR>
     * totalRecords<BR>
     * <BR>
     */
    public String totalRecords;

    /**
     * �i�\���y�[�W�ԍ��j<BR>
     * <BR>
     * �\���y�[�W�ԍ�<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * �i�ڋq�����ʎ����~���ꗗ�j
     * �ڋq�����ʎ����~���ꗗ
     * ----<English>--------------------
     * accProductTradeStopInfo
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit[] accProductTradeStopInfo;

    /**
     * @@roseuid 41FD930902FD
     */
    public WEB3AdminPMAccProductTradeStopListResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMAccProductTradeStopListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
