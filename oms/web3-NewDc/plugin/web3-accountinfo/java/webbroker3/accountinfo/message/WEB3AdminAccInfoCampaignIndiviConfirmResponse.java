head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX�m�Fڽ��ݽ
                       (WEB3AdminAccInfoCampaignIndiviConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 �Ј��� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX�m�Fڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX�m�Fڽ��ݽ<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviConfirmResponse extends WEB3GenResponse 
{
    /**          
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignIndiviConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312040L;
    
    /**
     * (�x���t���O)<BR>
     * �x���t���O<BR>
     * <BR>
     * 0�F�x���Ȃ�<BR>
     * 1�F�x������<BR>
     */
    public String alertFlag;
    
    /**
     * �萔�������L�����y�[���������
     */
    public WEB3AccInfoCampaignInfo commissionCampaignInfo;
    
    /**
     * @@roseuid 45C08761018B
     */
    public WEB3AdminAccInfoCampaignIndiviConfirmResponse() 
    {
     
    }
    
    /**
     * (�Ǘ��҂��q�l���ڋq��{���⍇�����X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmResponse
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoCampaignIndiviConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
