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
filename	WEB3AdminToManualLapseStatusResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮���������X�e�[�^�X�m�F���X�|���X(WEB3AdminToManualLapseStatusResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮���������X�e�[�^�X�m�F���X�|���X)<BR>
 * �g���K�[�����Ǘ��ҁE�蓮���������X�e�[�^�X�m�F���X�|���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToManualLapseStatusResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_status";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603161700L;

    /**
     * (�����X�e�[�^�X)<BR>
     * �����X�e�[�^�X<BR>
     * <BR>
     * 1�F�@@������<BR>
     * 5�F�@@������<BR>
     * 9�F�@@�G���[<BR>
     */
    public String lapseStatus;
    
    /**
     * (���ݎ���)<BR>
     * ���ݎ���<BR>
     */
    public Date currentTime;
    
    /**
     * @@roseuid 44192EEC0251
     */
    public WEB3AdminToManualLapseStatusResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminToManualLapseStatusResponse(WEB3AdminToManualLapseStatusRequest l_request)
    {
        super(l_request);
    } 
}
@
