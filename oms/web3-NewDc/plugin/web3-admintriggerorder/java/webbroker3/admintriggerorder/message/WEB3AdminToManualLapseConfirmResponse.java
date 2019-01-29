head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualLapseConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�����m�F���X�|���X(WEB3AdminToManualLapseConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�����m�F���X�|���X)<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�����m�F���X�|���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToManualLapseConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603161700L;
 
    /**
     * (�Ώے�������)<BR>
     * �����Ώۂ̒�������<BR>
     */
    public String lapseTargetOrderNumber;
    
    /**
     * (���ݎ���)<BR>
     * ���ݎ���<BR>
     */
    public Date currentTime;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName = null;
    
    /**
     * (������������)<BR>
     */
    public WEB3AdminToEquityOrderRefUnit equityOrderUnit;
    
    /**
     * (�敨OP��������)<BR>
     */
    public WEB3AdminToIfoOrderRefUnit ifoOrderUnit;
    
    /**
     * (������������)<BR>
     */
    public WEB3MarginContractUnit[] equityContractUnits;
    
    /**
     * (�敨OP���ʖ���)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] ifoContractUnits;
    
    /**
     * @@roseuid 44193091009C
     */
    public WEB3AdminToManualLapseConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminToManualLapseConfirmResponse(WEB3AdminToManualLapseConfirmRequest l_request)
    {
        super(l_request);
    } 
}
@
