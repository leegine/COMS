head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX�m�Fڽ��ݽ
                       (WEB3AdminAccInfoCampaignAccOpenConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 �Ј��� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX�m�Fڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX�m�Fڽ��ݽ<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignAccOpenConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312032L;
    
    /**
     * (�x���t���O)<BR>
     * �x���t���O<BR>
     * <BR>
     * 0�F�x���Ȃ�<BR>
     * 1�F�x������<BR>
     */
    public String alertFlag;
    
    /**
     * �����J�ݏ����w��L�����y�[���ύX����
     */
    public WEB3AccInfoCampaignInfo commissionCampaignInfo;
    
    /**
     * @@roseuid 45C0875F0360
     */
    public WEB3AdminAccInfoCampaignAccOpenConfirmResponse() 
    {
     
    }
    
    /**
     * (�Ǘ��҂��q�l���ڋq��{���⍇�����X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmResponse
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoCampaignAccOpenConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
