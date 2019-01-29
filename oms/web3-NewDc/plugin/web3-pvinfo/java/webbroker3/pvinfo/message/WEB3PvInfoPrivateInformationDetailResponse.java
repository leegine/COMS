head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ײ�ްĲ�̫Ұ��ݏڍ׃��X�|���X(WEB3PvInfoPrivateInformationDetailResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/25 ������(���u) �쐬
Revesion History : 2006/09/11 �����F(���u) �d�l�ύX���f��070
Revesion History : 2007/02/26 �֔�(���u) �d�l�ύX���f��073
Revesion History : 2007/03/16 �֔�(���u) �d�l�ύX���f��076
Revesion History : 2008/10/08 ���m�a(���u) �d�l�ύX���f��109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (��ײ�ްĲ�̫Ұ��ݏڍ׃��X�|���X)<BR>
 * �v���C�x�[�g�C���t�H���[�V�����ڍ׃��X�|���X�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoPrivateInformationDetailResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_privateInformationDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�\�����eID)<BR>
     * �\�����eID<BR>
     */
    public String displayContentsId;
    
    /**
     * (�\���^�C�g��)<BR>
     * �\���^�C�g��<BR>
     */
    public String displayTitle;
    
    /**
     * (�ŏI�X�V����)<BR>
     * �ŏI�X�V����<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (URL�����N)<BR>
     * URL�����N<BR>
     */
    public String urlLink;
    
    /**
     * (�\������)<BR>
     * �\������<BR>
     */
    public String displayMessage;
    
    /**
     * (���֋����)<BR>
     */
    public WEB3PvInfoAdvanceUnit[] advanceUnits;
    
    /**
     * (���ϊ����ԋ߂̌��ʏ��[])<BR>
     */
    public WEB3PvInfoSettleContractUnit[] settleContractUnits;
    
    /**
     * (�o����~���)<BR>
     * �o����~���<BR>
     */
    public WEB3PvInfoCashoutStopUnit cashoutStopUnit;

    /**
     * (�萔�������L�����y�[�����)<BR>
     * �萔�������L�����y�[�����<BR>
     */
    public WEB3PvInfoCommissionCampaignUnit[] commissionCampaignUnits;

    /**
     * (�s���������\�����)<BR>
     * �s���������\�����<BR>
     */
    public WEB3PvInfoShortfallGenerationInfo shortfallGenerationInfo;

    /**
     * (��ꐅ���Ǐؕ\�����)<BR>
     * ��ꐅ���Ǐؕ\�����<BR>
     */
    public WEB3PvInfoFirstAdditionalInfo firstAdditionalInfo;

    /**
     * (��񐅏��Ǐؕ\����� )<BR>
     * ��񐅏��Ǐؕ\����� <BR>
     */
    public WEB3PvInfoSecondAdditionalInfo secondAdditionalInfo;
    
    /**
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3PvInfoPrivateInformationDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
