head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignRegistAccListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰ݓo�^�ڋq�Ɖ�ڽ��ݽ(WEB3AdminAccInfoCampaignRegistAccListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l���萔����������߰ݓo�^�ڋq�Ɖ�ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l��� �萔�������L�����y�[���o�^�ڋq�Ɖ�X�|���X<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignRegistAccList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312047L;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * �萔�������L�����y�[���o�^�ڋq���
     */
    public WEB3AccInfoCampaignRegistAccountInfo registAccountInfo[];
    
    /**
     * @@roseuid 45C087620294
     */
    public WEB3AdminAccInfoCampaignRegistAccListResponse() 
    {
     
    }
    
    /**
     * (�Ǘ��҂��q�l���ڋq��{���⍇�����X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListResponse
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoCampaignRegistAccListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
