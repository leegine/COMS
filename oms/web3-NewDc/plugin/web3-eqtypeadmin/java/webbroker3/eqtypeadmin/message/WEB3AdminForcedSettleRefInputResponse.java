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
filename	WEB3AdminForcedSettleRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����Ɖ���̓��X�|���X(WEB3AdminForcedSettleRefInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ��іQ (���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������ϒ����Ɖ���̓��X�|���X)<BR>
 * �Ǘ��ҁE�������ϒ����Ɖ���̓��X�|���X�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleRefInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_ref_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;
    
    /**
     * (�������ꗗ)<BR>
     * �������ꗗ<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (���ϊ����ꗗ)<BR>
     * ���ϊ����ꗗ<BR>
     */
    public Date[] settleTimeLimitList;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�ꗗ<BR>
     */
    public String[] marketCodeList;
    
    /**
     * (�����G���[���R�R�[�h�ꗗ)<BR>
     * �����G���[���R�R�[�h�ꗗ<BR>
     * �ȉ��̒l�ō\�������z��<BR>
     * <BR>
     * 0005�F�@@�����c���s���G���[<BR>
     * 0006�F�@@������~�����G���[<BR>
     * 0016�F�@@���ϊ��������σG���[<BR>
     * 0017�F�@@�����E���n�����o�^�σG���[<BR>
     * 9001�F�@@���̑��G���[<BR>
     */
    public String[] errorReason;
    
    /**
     * (�������ϗ��R�ꗗ)<BR>
     * �������ϗ��R�ꗗ<BR>
     */
    public WEB3AdminForcedSettleReasonUnit[] forcedSettleReasonList;
    
    /**
     * @@roseuid 462CA429013D
     */
    public WEB3AdminForcedSettleRefInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminForcedSettleRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
