head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�T�[�r�X�N�����N�G�X�g(WEB3SrvRegiExecRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
Revesion History : 2005/10/05 ��� ���R�I(SRA) �g�����X�����N�Ή�
Revesion History : 2008/02/29 ���V�� �d�l�ύX���f��No.329
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiFutOpTaxDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMarginTaxDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�T�[�r�X���p�T�[�r�X�N�����N�G�X�g)<BR>
 * �T�[�r�X���p�T�[�r�X�N�����N�G�X�g�N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiExecRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3SrvRegiExecRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_exec";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151414L;
    
    /**
     * (ID)<BR>
     * �T�[�r�X�敪<BR>
     */
    public String serviceDiv;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * Token <BR>
     * �i���e���N���A�،� MULTEX ��p�j<BR>
     */
    public String token;
    
    /**
     * �M�p�����敪 <BR>
     * 0:�M�p�������J�� <BR>
     * 1:�J�ݍς�<BR>
     */
    public String marginTaxDiv;
    
    /**
     * �敨OP�����敪�i��؁j<BR> 
     * 0:�敨OP�������J�� <BR>
     * 1:�I�v�V�����J�� <BR>
     * 2:�敨�J�� <BR>
     * 3:�敨�I�v�V�����J��<BR>
     */
    public String futOpTaxDiv;
    
    /**
     * (�\���`�F�b�N�敪)<BR> 
     * �T�[�r�X���N������ہA�ڋq�̐\���󋵃`�F�b�N���s�����ǂ����𔻒肷��B<BR> 
     * true:�`�F�b�N�����{���� <BR>
     * false:�`�F�b�N�����{���Ȃ�<BR>
     */
    public boolean applyCheckDiv;
    
    //�I���b�N�X.�����J�ݑΉ�
    /**
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
    
    /**
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * �^�C�v<BR>
     */
    public String type;
    
    /**
     * SSID�l<BR>
     */
    public String ssidValue;

    /**
     * ���o�C���t���O<BR>
     * 1�F���o�C��<BR>
     * null�FPC<BR>
     */
    public String mobileFlag;

    /**
     * (�T�[�r�X���p�T�[�r�X�N�����N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F77E1F013A
     */
    public WEB3SrvRegiExecRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�T�[�r�X�N�����X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F77EBC0224
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3SrvRegiExecResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) ID�̃`�F�b�N<BR>
     *  1-1) this.ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     *  1-2) this.ID�̌������A2���܂���4���ȊO�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * 2) �M�p�����敪�̃`�F�b�N<BR> 
     * 2-1) this.�M�p�����敪==null�̏ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01837<BR>
     * 2-2) this.�M�p�����敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
�@@�@@ *     �E�M�p�������J�� <BR>
     * �@@�@@�E�J�ݍς� <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01838<BR>
     * <BR>
     * 3) �敨OP�����敪�i��؁j�̃`�F�b�N<BR> 
     * 3-1) this.�敨OP�����敪�i��؁j==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01839<BR> 
     * 3-2) this.�敨OP�����敪�i��؁j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�E�敨OP�������J�� <BR>
     * �@@�@@�E�I�v�V�����J�� <BR>
     * �@@�@@�E�敨�J�� <BR>
     * �@@�@@�E�敨�I�v�V�����J��<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01840<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 40F77EBC0205
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //1-1) this.ID==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + STR_METHOD_NAME); 
        }
        
        //1-2) this.ID�̌������A2���܂���4���ȊO�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv.length() != 2 &&
            this.serviceDiv.length() != 4)
        { 
            log.debug("******************************");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                getClass().getName() + STR_METHOD_NAME);    
        }
        
        //2-1) this.�M�p�����敪==null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.marginTaxDiv))
        {
            log.debug("�M�p�����敪==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01837,
                getClass().getName() + STR_METHOD_NAME,
                "�M�p�����敪�����w��ł��B");   
        }
        
        //2-2) this.�M�p�����敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvRegiMarginTaxDivDef.NOT_OPEN.equals(marginTaxDiv) && 
            !WEB3SrvRegiMarginTaxDivDef.OPEN.equals(marginTaxDiv))
        { 
            log.debug("�M�p�����敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01838,
                getClass().getName() + STR_METHOD_NAME,
                "�M�p�����敪�����݂��Ȃ��R�[�h�l�ł��B");     
        }
        
        //3-1) this.�敨OP�����敪�i��؁j==null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.futOpTaxDiv))
        {
            log.debug("�敨OP�����敪�i��؁j==null�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01839,
                getClass().getName() + STR_METHOD_NAME,
                "�敨OP�����敪�i��؁j�����w��ł��B");   
        }
        
        //3-2) this.�敨OP�����敪�i��؁j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN.equals(futOpTaxDiv) && 
            !WEB3SrvRegiFutOpTaxDivDef.OPTION_OPEN.equals(futOpTaxDiv) && 
            !WEB3SrvRegiFutOpTaxDivDef.FUTURE_OPEN.equals(futOpTaxDiv) && 
            !WEB3SrvRegiFutOpTaxDivDef.FUTURE_OPTION_OPEN.equals(futOpTaxDiv))
        { 
            log.debug("�敨OP�����敪�i��؁j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01840,
                getClass().getName() + STR_METHOD_NAME,
                "�敨OP�����敪�i��؁j�����݂��Ȃ��R�[�h�l�ł��B");     
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
