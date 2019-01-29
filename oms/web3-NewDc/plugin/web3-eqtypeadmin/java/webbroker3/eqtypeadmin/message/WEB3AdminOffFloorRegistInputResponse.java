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
filename	WEB3AdminOffFloorRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������V�K�o�^���̓��X�|���X(WEB3AdminOffFloorRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������V�K�o�^���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��җ���O���������V�K�o�^�T�[�r�X�i���͉�ʕ\���j�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * -----<English>------------<BR>
 * <BR>
 * WEB3AdminOffFloorRegistInputResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorRegistService(input screen)<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_regist_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (�s��R�[�h�ꗗ)<BR>
     * <BR>
     * �s��R�[�h�ꗗ�B<BR>
     * <BR>
     * marketList<BR>
     * <BR>
     */
    public String[] marketList;

    /**
     * (��t�J�n����)<BR>
     * <BR>
     * ��t�J�n�����B<BR>
     * �i��ʂɕ\������A���̓f�t�H���g�l�j<BR>
     * <BR>
     * -----<English>----------------<BR>
     * <BR>
     * orderStartDatetime<BR>
     * <BR>
     * �iinput default value displayed on the screen�j<BR>
     * <BR>
     */
    public Date orderStartDatetime;

    /**
     * (��t�I������)<BR>
     * <BR>
     * ��t�I�������B<BR>
     * �i��ʂɕ\������AHHMMSS�̕�����j<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * orderEndTime<BR>
     * �istring of HHMMSS displayed on the screen�j<BR>
     * <BR>
     */
    public String orderEndTime;

    /**
     * @@roseuid 421AE4880235
     */
    public WEB3AdminOffFloorRegistInputResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
