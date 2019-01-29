head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��X�|���X(WEB3AdminSrvRegiServiceDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
Revesion History : 2007/06/06 �����F (���u) ���f��256
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��X�|���X)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��X�|���X�N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceDetailsResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceDetails";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (�T�[�r�X����)
     */
    public String serviceName;
    
    /**
     * (�E�v)
     */
    public String summary;
    
    /**
     * (�\���敪)<BR>
     * 0:�s�v�@@1:�v<BR>
     */
    public String applyDiv;
    
    /**
     * (�d�q���ݒ�敪)<BR>
     * true:�ݒ�L�@@false:�ݒ薳<BR>
     */
    public boolean elePigeonDiv;
    
    /**
     * (�X�e�[�^�X)<BR>
     * 0:��~���@@1:�񋟒��i�\���s�j�@@2:�񋟒�<BR>
     */
    public String serviceStatus;
    
    /**
     * (���p���ԗ������)
     */
    public WEB3SrvRegiChargeInfo[] chargeInfo;
    
    /**
     * (���p���ԒP�ʋ敪)<BR>
     * 1:�N�@@2:���@@3:��<BR>
     * �i���p���Ԃ������ꍇ�Anull�j<BR>
     */
    public String trialDiv;
    
    /**
     * (���p����)<BR>
     * �i���p���Ԃ������ꍇ�Anull�j<BR>
     */
    public String trialPeriod;
    
    /**
     * (�\���\���ԁi���j)
     */
    public String applyAbleStartDate;
    
    /**
     * (�\���\���ԁi���j)
     */
    public String applyAbleEndDate;
    
    /**
     * (���ӏ�����)
     */
    public String consentSentence;
    
    /**
     * (�T�[�r�X���e)
     */
    public String serviceContent;
    
    /**
     * (�T�[�r�X����URL)
     */
    public String explainURL;
    
    /**
     * (���[���敪�i�m�F���[���j)<BR>
     * 0:���M���Ȃ��@@1:���M����<BR>
     */
    public String confirmMailDiv;
    
    /**
     * (���o�l�i�m�F���[���j)
     */
    public String confirmMailFrom;
    
    /**
     * (�����i�m�F���[���j)
     */
    public String confirmMailSubject;
    
    /**
     * (���[���w�b�_�[�i�m�F���[���j)
     */
    public String confirmMailHeader;
    
    /**
     * (���[���{���i�m�F���[���j)
     */
    public String confirmMailBody;
    
    /**
     * (���[���t�b�^�[�i�m�F���[���j)
     */
    public String confirmMailFooter;
    
    /**
     * (���[���敪�i�_��������[���j)<BR>
     * 0:���M���Ȃ��@@1:���M����<BR>
     */
    public String noticeMailDiv;
    
    /**
     * (���[�����M���i�_��������[���j)
     */
    public String noticeMailDate;
    
    /**
     * (���o�l�i�_��������[���j)
     */
    public String noticeMailFrom;
    
    /**
     * (�����i�_��������[���j)
     */
    public String noticeMailSubject;
    
    /**
     * (���[���w�b�_�[�i�_��������[���j)
     */
    public String noticeMailHeader;
    
    /**
     * (���[���{���i�_��������[���j)
     */
    public String noticeMailBody;
    
    /**
     * (���[���t�b�^�[�i�_��������[���j)
     */
    public String noticeMailFooter;
    
    /**
     * (���I�敪)<BR>
     * 0:���@@1:�L<BR>
     */
    public String lotteryDiv;
    
    /**
     * (��W���ԏ��)
     */
    public webbroker3.srvregi.message.WEB3SrvRegiLotteryInfo[ ] applyInfo;
    
    /**
     * (URL)
     */
    public String url;
    
    /**
     * (�񋟌`��)<BR>
     * NULL�F�����t�����s��Ȃ�<BR> 
     * 0�F�����񋟂̂݁i�����B�����ɖ����񋟁A���B�����ɖ��񋟁j<BR> 
     * 1�F�L���^�����񋟁i�����B�����ɖ����񋟁A���B�����ɗL���񋟁j<BR>
     */
    public String offerType;
    
    /**
     * (�萔����������v�z)
     */
    public String commissionAttainTotal;
    
    /**
     * (�ݒ�\�萔������)<BR>
     * �ݒ�\�Ȏ萔�������̈ꗗ<BR>
     */
    public WEB3SrvRegiSetAbleCommissionCondition[] setAbleCommissionConditions;

    /**
     * (�K�p�萔������)<BR>
     * �K�p���Ă���萔�������̈ꗗ<BR>
     */
    public WEB3SrvRegiApplyCommissionCondition[] applyCommissionConditions;
    
    /**
     * (��QURL)<BR>
     */
    public String url2;
    
    /**
     * (�n�b�V���v�Z�����敪)<BR>
     * 0:�w�薳    1:MD2    2:MD5    3:SHA-1    4:SHA-256<BR>
     * 5:SHA-384   6:SHA-512<BR>
     */
    public String hashCalHowToDiv;
    
    /**
     * (�n�b�V���v�Z�菇�敪)<BR>
     * 0:�w�薳    1:�d�q��    2:�ʏ�v�Z�i�P�j    3:�ʏ�v�Z�i�Q�j<BR>
     * 4:�Q�i�K�v�Z 5:���O�C���F��<BR>
     */
    public String hashCalOrderDiv;
    
    /**
     * (���M���@@�敪)<BR>
     * 0:GET    1:POST    2:HTTP-REQUEST<BR>
     * 3:����i�P�j�|���e���N���A�،� MULTEX ��p<BR>
     * 4:����i�Q�j�|���e���N���A�،� ���o�e���R��21 ��p<BR>
     */
    public String sendHowToDiv;
    
    /**
     * (���M�p�����[�^�敪)<BR>
     * 0:��    1:�L<BR>
     */
    public String sendParamDiv;
    
    /**
     * (�Í����ڋq�R�[�h�敪)<BR>
     * 0:��    1:�L<BR>
     */
    public String cryptAccountCodeDiv;
    
    /**
     * (�n�b�V���l�ꗗ)<BR>
     */
    public WEB3SrvRegiExecKey[] hashList;
    
    /**
     * (���M�p�����[�^�ꗗ)<BR>
     */
    public WEB3SrvRegiExecKey[] paramList;

    /**
     * (�����Ώۊ���)<BR>
     */
    public String freeTargetPeriod;

    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE4E110035
     */
    public WEB3AdminSrvRegiServiceDetailsResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminSrvRegiServiceDetailsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
