head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondConfConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������ݒ�m�F���X�|���X�N���X (WEB3AdminPMProductCondConfConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �Ǘ��ҁE�������������ݒ�m�F���X�|���X�N���X<BR>
 * <BR>
 * �Ǘ��ҁE�������������ݒ芮�����X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondConfConfirmResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfConfirmResponse extends WEB3GenResponse
{
    /**
    * PTYPE<BR>
    */
    public static final String PTYPE = "admin_pm_product_cond_conf_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502011606L;

    /**
     * �i���ݓ����j<BR>
     * <BR>
     * ���ݓ���<BR>
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * �i����K���ꗗ�j<BR>
     * <BR>
     * ������������K���̈ꗗ<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] tradingRegulationList;

    /**
     * �i��{���ꗗ�j<BR>
     * <BR>
     * ����������{���̈ꗗ<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] basicInfoList;

    /**
     * �i�M�p�������ꗗ�j<BR>
     * <BR>
     * ���������M�p�������̈ꗗ<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] stockMarginInfoList;

    /**
     * �i�ϑ��ۏ؋����ꗗ�j<BR>
     * <BR>
     * ���������ϑ��ۏ؋����̈ꗗ
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] depositRateList;

    /**
     * �i��p�L���،����ꗗ�j<BR>
     * <BR>
     * ����������p�L���،����̈ꗗ<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] substituteSecurityInfoList;

    /**
     * �i�l�i���ꗗ�j<BR>
     * <BR>
     * ���������l�i���̈ꗗ<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] priceInfoList;

    /**
     * @@roseuid 41FA2A2C039A
     */
    public WEB3AdminPMProductCondConfConfirmResponse()
    {

    }

    /**
     * @@roseuid 41FD94070000
     * @@param l_request l_request
     */
    public WEB3AdminPMProductCondConfConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
