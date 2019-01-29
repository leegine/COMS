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
filename	WEB3AdminOffFloorChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������X�V���̓��X�|���X (WEB3AdminOffFloorChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������X�V���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��җ���O���������X�V�T�[�r�X�i���͉�ʕ\���j�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * ----<English>----------<BR>
 * <BR>
 * WEB3AdminOffFloorChangeInputResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorChangeService(input screen)<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOffFloorChangeInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_change_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (������)<BR>
     * <BR>
     * �������B<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * (��t�J�n����)<BR>
     * <BR>
     * ��t�J�n�����B<BR>
     * <BR>
     * orderStartDatetime<BR>
     * <BR>
     */
    public Date orderStartDatetime;

    /**
     * (�������i)<BR>
     * <BR>
     * �������i�B<BR>
     * <BR>
     * offFloorOrderPrice<BR>
     * <BR>
     */
    public String offFloorOrderPrice;

    /**
     * (�\���������)<BR>
     * <BR>
     * �\����������B<BR>
     * �i��l������̒����\�����̏���l�j<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * maxApplyQuantity<BR>
     * �imaximum value of applyQuantity per person�j<BR>
     * <BR>
     */
    public String maxApplyQuantity;

    /**
     * @@roseuid 421AE3B2036E
     */
    public WEB3AdminOffFloorChangeInputResponse()
    {

    }

    /**
      * @@roseuid 41FD94160000
      * @@param l_request l_request
      */
    public WEB3AdminOffFloorChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
