head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^���ʃ��N�G�X�g(WEB3AdminSrvRegiServiceRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
Revesion History : 2009/05/27 �đo�g(���u) �d�l�ύX���f��424
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3HashCalHowToDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalOrderDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMailDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiSendHowToDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^���ʃ��N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^���ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceRegistCommonRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceRegistCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceRegistCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151450L;
    
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
     * (���I�敪)<BR>
     * 0:���@@1:�L<BR>
     */
    public String lotteryDiv;
    
    /**
     * (�\���敪)<BR>
     * 0:�s�v�@@1:�v<BR>
     */
    public String applyDiv;
    
    /**
     * (���[���敪�i�m�F���[���j)<BR>
     * 0:���M���Ȃ��@@1:���M����<BR>
     */
    public String confirmMailDiv;
    
    /**
     * (���[���敪�i�_��������[���j)<BR>
     * 0:���M���Ȃ��@@1:���M����<BR>
     */
    public String noticeMailDiv;
    
    /**
     * (URL)
     */
    public String url;
    
    /**
     * (��QURL)
     */
    public String url2;
    
    /**
     * (�n�b�V���v�Z�����敪)<BR>
     * 0:�w�薳 <BR>
     * 1:MD2 <BR>
     * 2:MD5 <BR>
     * 3:SHA-1 <BR>
     * 4:SHA-256 <BR>
     * 5:SHA-384 <BR>
     * 6:SHA-512<BR>
     */
    public String hashCalHowToDiv;
    
    /**
     * (�n�b�V���v�Z�菇�敪)<BR>
     * 0:�w�薳 <BR>
     * 1:�d�q�� <BR>
     * 2:�ʏ�v�Z�i�P�j<BR> 
     * 3:�ʏ�v�Z�i�Q�j <BR>
     * 4:�Q�i�K�v�Z<BR>
     * 5:���O�C���F��<BR>
     * 7:�V���O���T�C���I���A�g<BR>
     */
    public String hashCalOrderDiv;
    
    /**
     * (���M���@@�敪)<BR>
     * 0:GET <BR>
     * 1:POST <BR>
     * 2:HTTP-REQUEST<BR> 
     * 3:����i�P�j�|���e���N���A�،� MULTEX ��p<BR> 
     * 4:����i�Q�j�|���e���N���A�،� ���o�e���R��21 ��p<BR>
     */
    public String sendHowToDiv;
    
    /**
     * (���M�p�����[�^�敪)<BR>
     * 0:�� <BR>
     * 1:�L<BR>
     */
    public String sendParamDiv;
    
    /**
     * (�Í����ڋq�R�[�h�敪)<BR>
     * 0:�� <BR>
     * 1:�L<BR>
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
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^���ʃ��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F255EF03B8
     */
    public WEB3AdminSrvRegiServiceRegistCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     *  1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.�\���敪=="�v"�ł���Athis.�T�[�r�X�敪�̌���!=2���̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00880<BR>
     *  1-3) this.�\���敪=="�s�v"�ł���Athis.�T�[�r�X�敪�̌���!=4���̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00881<BR>
     *  1-4) this.�T�[�r�X�敪�ɐ��l�ȊO���i�[����Ă���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00882<BR>
     * <BR>
     * 2) �T�[�r�X���̂̃`�F�b�N<BR>
     *  2-1) this.�T�[�r�X����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00883<BR>
     *  2-2) 1������this.�T�[�r�X���̂̌�����100Byte�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00884<BR>
     * <BR>
     * 3) �E�v�̃`�F�b�N<BR>
     *  3-1) this.�\���敪=="�v"�ł���A����this.�E�v==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00885<BR>
     *  3-2) this.�\���敪=="�s�v"�ł���A����this.�E�v!=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00886<BR>
     *  3-3) this.�E�v�ɔ��p�J�i�ȊO���Z�b�g����Ă���ꍇ�A��O���X���[����B(*1)<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00887<BR>
     *  3-4) this.�E�v�̕�������25�����ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00888<BR>
     * <BR>
     * 4) �\���敪�̃`�F�b�N<BR>
     *  4-1) this.�\���敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00891<BR>
     *  4-2) this.�\���敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�v"<BR>
     * �@@�@@�@@"�s�v"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00892<BR>
     * <BR>
     * 5) ���I�敪�̃`�F�b�N<BR>
     *  5-1) this.�\���敪=="�v"�ł���A����this.���I�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00895<BR>
     *  5-2) this.���I�敪==null�ȊO�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"��"<BR>
     * �@@�@@�@@"�L"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00896<BR>
     * <BR>
     * 4) ���[���敪�i�m�F���[���j�̃`�F�b�N<BR>
     *  4-1) this.���[���敪�i�m�F���[���j==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00956<BR>
     *  4-2) this.���[���敪�i�m�F���[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"���M���Ȃ�"<BR>
     * �@@�@@�@@"���M����"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00927<BR>
     * <BR>
     * 5) ���[���敪�i�_��������[���j�̃`�F�b�N<BR>
     *  5-1) this.���[���敪�i�_��������[���j==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00928<BR>
     *  5-2) this.���[���敪�i�_��������[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"���M���Ȃ�"<BR>
     * �@@�@@�@@"���M����"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00929<BR>
     * <BR>
     * 6) �n�b�V���v�Z�����敪�̃`�F�b�N<BR> 
     * 6-1) this.�n�b�V���v�Z�����敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01841<BR> 
     * 6-2) this.�n�b�V���v�Z�����敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�E�w�薳 <BR>
     * �@@�@@�EMD2 <BR>
     * �@@�@@�EMD5 <BR>
     * �@@�@@�ESHA-1 <BR>
     * �@@�@@�ESHA-256 <BR>
     * �@@�@@�ESHA-384 <BR>
     * �@@�@@�ESHA-512 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01842<BR>
     * <BR>
     * 7) �n�b�V���v�Z�菇�敪�̃`�F�b�N<BR> 
     * 7-1) this.�n�b�V���v�Z�菇�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01843<BR>
     * 7-2) this.�n�b�V���v�Z�菇�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�E�w�薳 <BR>
     * �@@�@@�E�d�q�� <BR>
     * �@@�@@�E�ʏ�v�Z�i�P�j<BR> 
     * �@@�@@�E�ʏ�v�Z�i�Q�j <BR>
     * �@@�@@�E�Q�i�K�v�Z <BR>
     * �@@�@@�E���O�C���F�� <BR>
     * �@@�@@�E�V���O���T�C���I���A�g<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01844<BR>
     * <BR>
     * 8) ���M���@@�敪�̃`�F�b�N<BR> 
     * 8-1) this.���M���@@�敪==null�̏ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01845<BR>
     * 8-2) this.���M���@@�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�EGET <BR>
     * �@@�@@�EPOST <BR>
     * �@@�@@�EHTTP-REQUEST<BR> 
     * �@@�@@�E����i�P�j�|���e���N���A�،� MULTEX ��p<BR> 
     * �@@�@@�E����i�Q�j�|���e���N���A�،� ���o�e���R��21 ��p<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01846<BR>
     * <BR>
     * 9) ���M�p�����[�^�敪�̃`�F�b�N<BR> 
     * 9-1) this.���M�p�����[�^�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01847<BR>
     * 9-2) this.���M�p�����[�^�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�E�� <BR>
     * �@@�@@�E�L <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01848<BR>
     * <BR>
     * 10) �Í����ڋq�R�[�h�敪�̃`�F�b�N<BR> 
     * 10-1) this.�Í����ڋq�R�[�h�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01849<BR>
     * 10-2) this.�Í����ڋq�R�[�h�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�E�� <BR>
     * �@@�@@�E�L <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01850<BR>
     * <BR>
     * 11) �n�b�V���l�ꗗ�̃`�F�b�N<BR> 
     * �@@�n�b�V���l�ꗗ!=null�ł���A���v�f��>0���̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR> 
     * 11-1) �n�b�V���l�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01851<BR> 
     * 11-2) �n�b�V���l�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01852<BR>
     * <BR>
     * 12) ���M�p�����[�^�ꗗ�̃`�F�b�N<BR> 
     * �@@���M�p�����[�^�ꗗ!=null�ł���A���v�f��>0���̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR> 
     * 12-1) ���M�p�����[�^�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01853<BR>
     * 12-2) ���M�p�����[�^�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01854<BR>
     * <BR>
     * 13) �n�b�V���v�Z�����敪�A�n�b�V���v�Z�菇�敪�̃`�F�b�N<BR> 
     * 13-1) this.�n�b�V���v�Z�����敪!="�w�薳"�ł���A����this.�n�b�V���v�Z�菇�敪="�w�薳"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01855<BR>
     * �@@�@@�̏ꍇ�A��O���X���[����B <BR>
     * 13-2) this.�n�b�V���v�Z�����敪="�w�薳"�ł���A����this.�n�b�V���v�Z�菇�敪!="�w�薳"<BR> 
     * �@@�@@�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01855<BR>
     *<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 40F4DC3D018F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME); 
        }
                
        //1-2) this.�\���敪=="�v"�ł���Athis.�T�[�r�X�敪�̌���!=2���̏ꍇ�A
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            this.serviceDiv.length() != 2)
        {
            log.debug("1-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME); 
        }    

        //1-3) this.�\���敪=="�s�v"�ł���Athis.�T�[�r�X�敪�̌���!=4���̏ꍇ�A
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
        this.serviceDiv.length() != 4)
        {
            log.debug("1-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME); 
        }    

        //1-4) this.�T�[�r�X�敪�ɐ��l�ȊO���i�[����Ă���ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.serviceDiv))
        {
            log.debug("1-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //2) �T�[�r�X���̂̃`�F�b�N
        //2-1) this.�T�[�r�X����==null�̏ꍇ�A��O���X���[����B
        if (this.serviceName == null || "".equals(serviceName.trim()))
        {
            log.debug("2-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00883,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //2-2) 1������this.�T�[�r�X���̂̌�����100Byte�ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.serviceName.length() < 1 ||
            WEB3StringTypeUtility.getByteLength(this.serviceName) > 100)
        {
            log.debug("2-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00884,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //3) �E�v�̃`�F�b�N
        //3-1) this.�\���敪=="�v"�ł���A����this.�E�v==null�̏ꍇ�A��O���X���[����B
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.summary == null || "".equals(summary.trim())))
        {
            log.debug("3-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00885,
                getClass().getName() + STR_METHOD_NAME);     
        }

        // 3-2) this.�\���敪=="�s�v"�ł���A����this.�E�v!=null�̏ꍇ�A��O���X���[����B
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            (this.summary != null && summary.trim().length() != 0))
        {
            log.debug("3-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00886,
                getClass().getName() + STR_METHOD_NAME);     
        }

        //3-3) this.�E�v�ɔ��p�J�i�ȊO���Z�b�g����Ă���ꍇ�A��O���X���[����B
        if ((this.summary != null && summary.trim().length() != 0) && !WEB3StringTypeUtility.isSingle(this.summary))
        {
            log.debug("3-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00887,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //3-4) this.�E�v�̕�������25�����ł͂Ȃ��ꍇ�A��O���X���[����B
        if ((this.summary != null && summary.trim().length() != 0) && this.summary.length() > 25)
        {
            log.debug("3-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00888,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4) �\���敪�̃`�F�b�N
        //4-1) this.�\���敪==null�̏ꍇ�A��O���X���[����B
        if (this.applyDiv == null || "".equals(applyDiv.trim()))
        {
            log.debug("4-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00891,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4-2) this.�\���敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            !WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv))
        {
            log.debug("4-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00892,
                getClass().getName() + STR_METHOD_NAME);    
        }

        //5) ���I�敪�̃`�F�b�N
        //5-1) this.�\���敪=="�v"�ł���A����this.���I�敪==null�̏ꍇ�A��O���X���[����B
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.lotteryDiv == null || "".equals(lotteryDiv.trim())))
        {
            log.debug("5-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00895,
                getClass().getName() + STR_METHOD_NAME);      
        }

        //5-2) this.���I�敪==null�ȊO�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if ((this.lotteryDiv != null && lotteryDiv.trim().length() != 0) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.lotteryDiv))
        {
            log.debug("5-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00844,
                getClass().getName() + STR_METHOD_NAME);  
        }

        //4) ���[���敪�i�m�F���[���j�̃`�F�b�N
        //4-1) this.���[���敪�i�m�F���[���j==null�̏ꍇ�A��O���X���[����B
        if (this.confirmMailDiv == null || "".equals(confirmMailDiv.trim()))
        {
            log.debug("this.���[���敪�i�m�F���[���j==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00956,
                getClass().getName() + STR_METHOD_NAME);  
        }

        //4-2) this.���[���敪�i�m�F���[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.confirmMailDiv) &&
            !WEB3SrvRegiMailDivDef.NOT_SEND_MAIL.equals(this.confirmMailDiv))
        {
            log.debug("this.���[���敪�i�m�F���[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00927,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5) ���[���敪�i�_��������[���j�̃`�F�b�N
        //5-1) this.���[���敪�i�_��������[���j==null�̏ꍇ�A��O���X���[����B
        if (this.noticeMailDiv == null || "".equals(noticeMailDiv.trim()))
        {
            log.debug("this.���[���敪�i�_��������[���j==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00928,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5-2) this.���[���敪�i�_��������[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.noticeMailDiv) &&
            !WEB3SrvRegiMailDivDef.NOT_SEND_MAIL.equals(this.noticeMailDiv))
        {
            log.debug("this.���[���敪�i�_��������[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00929,
                getClass().getName() + STR_METHOD_NAME);
        } 
        
        //* 6) �n�b�V���v�Z�����敪�̃`�F�b�N<BR> 
        //* 6-1) this.�n�b�V���v�Z�����敪==null�̏ꍇ�A��O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalHowToDiv))
        {
            log.debug("�n�b�V���v�Z�����敪==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01841,
                getClass().getName() + STR_METHOD_NAME,
                "�n�b�V���v�Z�����敪==null�̏ꍇ�A��O���X���[����"); 
        }
        
        //* 6-2) this.�n�b�V���v�Z�����敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
        //* �@@�@@�E�w�薳 <BR>
        //* �@@�@@�EMD2 <BR>
        //* �@@�@@�EMD5 <BR>
        //* �@@�@@�ESHA-1 <BR>
        //* �@@�@@�ESHA-256 <BR>
        //* �@@�@@�ESHA-384 <BR>
        //* �@@�@@�ESHA-512 <BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) && 
            !WEB3HashCalHowToDivDef.MD2.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.MD5.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_1.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_256.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_384.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_512.equals(this.hashCalHowToDiv)) 
        {
            log.debug("�n�b�V���v�Z�����敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01842,
                getClass().getName() + STR_METHOD_NAME,
                "�n�b�V���v�Z�����敪�̒l���s��");     
        }

        //* 7) �n�b�V���v�Z�菇�敪�̃`�F�b�N<BR> 
        //* 7-1) this.�n�b�V���v�Z�菇�敪==null�̏ꍇ�A��O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalOrderDiv))
        {
            log.debug("�n�b�V���v�Z�菇�敪==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01843,
                getClass().getName() + STR_METHOD_NAME,
                "�n�b�V���v�Z�菇�敪==null�̏ꍇ�A��O���X���[����"); 
        } 
        //* 7-2) this.�n�b�V���v�Z�菇�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
        //* �@@�@@�E�w�薳 <BR>
        //* �@@�@@�E�d�q�� <BR>
        //* �@@�@@�E�ʏ�v�Z�i�P�j<BR> 
        //* �@@�@@�E�ʏ�v�Z�i�Q�j <BR>
        //* �@@�@@�E�Q�i�K�v�Z <BR>
        //* �@@�@@�E���O�C���F�� <BR>
        //* �@@�@@�E�V���O���T�C���I���A�g<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.ELE_PIGEON.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.NORMAL1.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.NORMAL2.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.TWO_STEP_CALCULATION.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.LOGIN_CERTIFICATION.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.SINGLE_SIGNON_COOPERATION.equals(this.hashCalOrderDiv))
        {
            log.debug("�n�b�V���v�Z�菇�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01844,
                getClass().getName() + STR_METHOD_NAME,
                "�n�b�V���v�Z�菇�敪�̒l���s��"); 
        }

        //* 8) ���M���@@�敪�̃`�F�b�N<BR> 
        //* 8-1) this.���M���@@�敪==null�̏ꍇ�A��O���X���[����B<BR> 
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.sendHowToDiv))
        {
            log.debug("���M���@@�敪==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01845,
                getClass().getName() + STR_METHOD_NAME,
                "���M���@@�敪==null�̏ꍇ�A��O���X���[����"); 
        } 
        
        //* 8-2) this.���M���@@�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
        //* �@@�@@�EGET <BR>
        //* �@@�@@�EPOST <BR>
        //* �@@�@@�EHTTP-REQUEST<BR> 
        //* �@@�@@�E����i�P�j�|���e���N���A�،� MULTEX ��p<BR> 
        //* �@@�@@�E����i�Q�j�|���e���N���A�،� ���o�e���R��21 ��p<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.sendHowToDiv) && 
            !WEB3SrvRegiSendHowToDivDef.GET.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.POST.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.HTTP_REQUEST.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.SPECIAL1.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.SPECIAL2.equals(this.sendHowToDiv))
        {
            log.debug("���M���@@�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01846,
                getClass().getName() + STR_METHOD_NAME,
                "���M���@@�敪�̒l���s��"); 
        } 

        //* 9) ���M�p�����[�^�敪�̃`�F�b�N<BR> 
        //* 9-1) this.���M�p�����[�^�敪==null�̏ꍇ�A��O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.sendParamDiv))
        {
            log.debug("���M�p�����[�^�敪==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01847,
                getClass().getName() + STR_METHOD_NAME,
                "���M�p�����[�^�敪==null�̏ꍇ�A��O���X���[����"); 
        } 
        
        //* 9-2) this.���M�p�����[�^�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
        //* �@@�@@�E�� <BR>
        //* �@@�@@�E�L <BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.sendParamDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.sendParamDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.sendParamDiv))
        {
            log.debug("���M�p�����[�^�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01848,
                getClass().getName() + STR_METHOD_NAME,
                "���M�p�����[�^�敪�̒l���s��"); 
        } 

        // * 10) �Í����ڋq�R�[�h�敪�̃`�F�b�N<BR> 
        //* 10-1) this.�Í����ڋq�R�[�h�敪==null�̏ꍇ�A��O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.cryptAccountCodeDiv))
        {
            log.debug("�Í����ڋq�R�[�h�敪==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01849,
                getClass().getName() + STR_METHOD_NAME,
                "�Í����ڋq�R�[�h�敪==null�̏ꍇ�A��O���X���[����"); 
        } 
        
        //* 10-2) this.�Í����ڋq�R�[�h�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
        //* �@@�@@�E�� <BR>
        //* �@@�@@�E�L <BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.cryptAccountCodeDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.cryptAccountCodeDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.cryptAccountCodeDiv))
        {
            log.debug("�Í����ڋq�R�[�h�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01850,
                getClass().getName() + STR_METHOD_NAME,
                "�Í����ڋq�R�[�h�敪�̒l���s��"); 
        } 

        //* 11) �n�b�V���l�ꗗ�̃`�F�b�N<BR> 
        //* �@@�n�b�V���l�ꗗ!=null�ł���A���v�f��>0���̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR> 
        if (this.hashList != null && this.hashList.length > 0)
        {
            int l_int = this.hashList.length;
            for (int i = 0; i < l_int; i++)
            {
                //11-1) �n�b�V���l�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����B <BR>
                if (hashList[i].keyKindDiv == null)
                {
                    log.debug("�n�b�V���l�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01851,
                        getClass().getName() + STR_METHOD_NAME,
                        "�n�b�V���l�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����"); 
                }
                
                //* 11-2) �n�b�V���l�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����B <BR>
                if (hashList[i].key == null)
                {
                    log.debug("�n�b�V���l�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01852,
                        getClass().getName() + STR_METHOD_NAME,
                        "�n�b�V���l�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����"); 
                }
                
                //��Q�Ή� NO_U01987
				//11-3)�n�b�V���l�ꗗ.���p�L�[�̃o�C�g����256�o�C�g���ȏ�̏ꍇ�A��O���X���[����B<BR>
				if ((hashList[i].key != null && hashList[i].key.trim().length() != 0) && 
					WEB3StringTypeUtility.getByteLength(hashList[i].key) > 256)
				{
					log.debug("11-3) �n�b�V���l�ꗗ.���p�L�[��256byte�ȏ�");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01831,
						getClass().getName() + STR_METHOD_NAME);
				}
            }

        }

        //* 12) ���M�p�����[�^�ꗗ�̃`�F�b�N<BR> 
        //* �@@���M�p�����[�^�ꗗ!=null�ł���A���v�f��>0���̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR>
        if (this.paramList != null && this.paramList.length > 0)
        { 
            int l_int = this.paramList.length;
            for (int i = 0; i < l_int; i++)
            {
                //* 12-1) ���M�p�����[�^�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����B <BR>
                if (paramList[i].keyKindDiv == null)
                {
                    log.debug("���M�p�����[�^�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01853,
                        getClass().getName() + STR_METHOD_NAME,
                        "���M�p�����[�^�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����"); 
                }
                
                //* 12-2) ���M�p�����[�^�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����B <BR>
                if (paramList[i].key == null)
                {
                    log.debug("���M�p�����[�^�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01854,
                        getClass().getName() + STR_METHOD_NAME,
                        "���M�p�����[�^�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����"); 
                }
                
				//��Q�Ή� NO_U01987
                //12-3)���M�p�����[�^�ꗗ.���p�L�[�̃o�C�g����256�o�C�g���ȏ�̏ꍇ�A��O���X���[����B<BR>
				if ((paramList[i].key != null && paramList[i].key.trim().length() != 0) && 
					WEB3StringTypeUtility.getByteLength(paramList[i].key) > 256)
				{
					log.debug("12-3) ���M�p�����[�^�ꗗ.���p�L�[��256byte�ȏ�");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01859,
						getClass().getName() + STR_METHOD_NAME);
				}
            }
        }

        //* 13) �n�b�V���v�Z�����敪�A�n�b�V���v�Z�菇�敪�̃`�F�b�N<BR> 
        // * 13-1) this.�n�b�V���v�Z�����敪!="�w�薳"�ł���A����this.�n�b�V���v�Z�菇�敪="�w�薳"<BR> 
        if (!WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
            WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv))
        {
            log.debug("�n�b�V���v�Z�����敪!=�w�薳�ł���A����this.�n�b�V���v�Z�菇�敪=�w�薳");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01855,
                getClass().getName() + STR_METHOD_NAME,
                "�n�b�V���v�Z�����敪!=�w�薳�ł���A����this.�n�b�V���v�Z�菇�敪=�w�薳"); 
        }
        
        //13-2) this.�n�b�V���v�Z�����敪="�w�薳"�ł���A����this.�n�b�V���v�Z�菇�敪!="�w�薳"<BR> 
        if (WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv))
        {
            log.debug("�n�b�V���v�Z�����敪=�w�薳�ł���A����this.�n�b�V���v�Z�菇�敪!=�w�薳");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01855,
                getClass().getName() + STR_METHOD_NAME,
                "�n�b�V���v�Z�����敪=�w�薳�ł���A����this.�n�b�V���v�Z�菇�敪!=�w�薳"); 
        }
        
        //��Q�Ή� NO_U01985
        //14-1)this.URL�̃o�C�g����256�o�C�g���ȏ�̏ꍇ�A��O���X���[����B<BR>
		if ((url != null && url.trim().length() != 0) && WEB3StringTypeUtility.getByteLength(url) > 256)
		{
			log.debug("14-1) URL �� 256byte�ȏ�");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}
		
		//��Q�Ή� NO_U01985
		//15-1)this.URL2�̃o�C�g����256�o�C�g���ȏ�̏ꍇ�A��O���X���[����B<BR>
		if ((url2 != null && url2.trim().length() != 0) && WEB3StringTypeUtility.getByteLength(url2) > 256)
		{
			log.debug("15-1) URL2 �� 256byte�ȏ�");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F46E903C8
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
