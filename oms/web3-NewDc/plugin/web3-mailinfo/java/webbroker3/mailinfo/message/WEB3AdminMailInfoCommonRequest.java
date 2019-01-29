head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[����񋤒ʃ��N�G�X�g(WEB3AdminMailInfoCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
Revesion History : 2004/10/20  ����(���u) �쐬
*/
package webbroker3.mailinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���[����񋤒ʃ��N�G�X�g)<BR>
 * ���[����񋤒ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoCommonRequest extends WEB3GenRequest
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
     private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoChangeInputRequest.class);
    
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (���M���[���敪)<BR>
     */
    public String sendMailDiv;
    
    /**
     * ����ID<BR>
     */
    public String discernId;

    /**
     * (���[������)<BR>
     */
    public String mailName;

    /**
     * (���o�l)<BR>
     */
    public String mailFrom;

    /**
     * (���M�惁�[���A�h���X)<BR>
     */
    public String sendAddress;

    /**
     * (����)<BR>
     */
    public String mailSubject;

    /**
     * (���[���w�b�_�[)<BR>
     */
    public String mailHeader;

    /**
     * (���[���{��)<BR>
     */
    public String mailBody;

    /**
     * (���[���t�b�^�[)<BR>
     */
    public String mailFooter;

    /**
     * @@roseuid 416F1DCE02CE
     */
    public WEB3AdminMailInfoCommonRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �v���O����ID�̃`�F�b�N<BR>
     *  1-1) this.���M���[���敪==null�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00862              <BR>
     *     =============================================== <BR>
     *  1-2) this.���M���[���敪�̌�����4���̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00863              <BR>
     *     =============================================== <BR>
     *  1-3) this.���M���[���敪�̒l�����l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00864              <BR>
     *     =============================================== <BR>
     * <BR>
     * 2) ����ID�̃`�F�b�N<BR>
     *  2-1) this.����ID==null�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00865              <BR>
     *     =============================================== <BR>
     *  2-2) this.����ID�̌�����4���̏ꍇ�A�܂��͌���==0���̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00866              <BR>
     *     =============================================== <BR>
     *  2-3) this.����ID�̒l�ɁA���p�p���ȊO�̕��������݂���ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00867              <BR>
     *     =============================================== <BR>
     * <BR>
     * 3) ���[�����̂̃`�F�b�N<BR>
     *  3-1) this.���[�����̂̒l��200�o�C�g�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00868              <BR>
     *     =============================================== <BR>
     * <BR>
     * 4) �����̃`�F�b�N<BR>
     *  4-1) this.����==null�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00871              <BR>
     *     =============================================== <BR>
     *  4-2) this.�����̒l��1000�o�C�g�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00872              <BR>
     *     =============================================== <BR>
     * <BR>
     * 5) ���[���w�b�_�[�^���[���{���^���[���t�b�^�[�̃`�F�b�N<BR>
     *  5-1) this.���[���w�b�_�[�Athis.���[���{���Athis.���[���t�b�^�[��<BR>
     * �@@�@@�S�Ă�null�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00873              <BR>
     *     =============================================== <BR>
     *  5-2) this.���[���w�b�_�[!=null�ł���A���l��4000�o�C�g�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00874           <BR>
     *     =============================================== <BR>
     *  5-3) this.���[���{��!=null�ł���A���l��4000�o�C�g�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00875           <BR>
     *     =============================================== <BR>
     *  5-4) this.���[���t�b�^�[!=null�ł���A���l��4000�o�C�g�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *     =============================================== <BR>
     *     �@@�@@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00876           <BR>
     *     =============================================== <BR>
     * <BR>
     * @@roseuid 413C11F903A9
     */
    public void validate() throws WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //(1) ���M���[���敪�̃`�F�b�N
        //(1-1) this.���M���[���敪==null�̏ꍇ�A��O���X���[����
        if(this.sendMailDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00862, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(1-2) this.���M���[���敪�̌�����4���̏ꍇ�A��O���X���[����
        if(WEB3StringTypeUtility.getByteLength(this.sendMailDiv) > 4)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00863, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(1-3) this.���M���[���敪�̒l�����l�ȊO�̏ꍇ�A��O���X���[����
        if( WEB3StringTypeUtility.isNumber(this.sendMailDiv) == false)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00864, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //( 2) ����ID�̃`�F�b�N
        //(2-1) this.����ID==null�̏ꍇ�A��O���X���[����B
        if(this.discernId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00865, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(2-2) this.����ID�̌�����4���̏ꍇ�A�܂��͌���==0���̏ꍇ�A
        //��O���X���[����B
        if((WEB3StringTypeUtility.getByteLength(this.discernId) > 4) || (WEB3StringTypeUtility.getByteLength(this.discernId) == 0 ))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00866, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //( 2-3) this.����ID�̒l�ɁA���p�p���ȊO�̕��������݂���ꍇ�A
        //��O���X���[����B
        if(WEB3StringTypeUtility.isSingle(this.discernId) == false)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00867, 
                this.getClass().getName() + STR_METHOD_NAME); 
           
        }
        //(3) ���[�����̂̃`�F�b�N
        //(3-1) this.���[�����̂̒l��200�o�C�g�̏ꍇ�A��O���X���[����
        if(WEB3StringTypeUtility.getByteLength(this.mailName) > 200 )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00868, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }

        //(4) �����̃`�F�b�N
        //(4-1) this.����==null�̏ꍇ�A��O���X���[����B
        if(this.mailSubject == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00871, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(4-2) this.�����̒l��1000�o�C�g�̏ꍇ�A��O���X���[����
        if(WEB3StringTypeUtility.getByteLength(this.mailSubject) > 1000)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00872, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(5) ���[���w�b�_�[�^���[���{���^���[���t�b�^�[�̃`�F�b�N
        //(5-1) this.���[���w�b�_�[�Athis.���[���{���Athis.���[���t�b�^�[�̑S�Ă�null�̏ꍇ�A��O���X���[����B
        if(this.mailHeader == null && this.mailBody == null && this.mailFooter == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00873, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(5-2) this.���[���w�b�_�[!=null�ł���A���l��4000�o�C�g�̏ꍇ�A��O���X���[����B
        if(this.mailHeader != null && WEB3StringTypeUtility.getByteLength(this.mailHeader) > 4000)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00874, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(5-3) this.���[���{��!=null�ł���A���l��4000�o�C�g�̏ꍇ�A��O���X���[����B
        if(this.mailBody != null && WEB3StringTypeUtility.getByteLength(this.mailBody) > 4000)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00875, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(5-4) this.���[���t�b�^�[!=null�ł���A���l��4000�o�C�g�̏ꍇ��O���X���[����B
        if(this.mailFooter != null && WEB3StringTypeUtility.getByteLength(this.mailFooter) > 4000)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00876, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F1DCE031C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoCommonResponse(this);
    }
}
@
