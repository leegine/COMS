head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX����ڽ��ݽ(WEB3AdminAccInfoCampaignAccOpenInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
Revision History : 2007/2/1  ���f��No.165
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX����ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX����ڽ��ݽ<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignAccOpenInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312035L;
    
    /**
     * �����J�ݏ����w�跬��߰ݕύX�O���
     */
    public WEB3AccInfoCampaignInfo commissionCampaignInfo;
    
    /**
     * @@roseuid 45C0876000DF
     */
    public WEB3AdminAccInfoCampaignAccOpenInputResponse() 
    {
     
    }
    
    /**
     * (�Ǘ��҂��q�l���ڋq��{���⍇�����X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputResponse
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoCampaignAccOpenInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
