head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ڍ׃��N�G�X�g(WEB3AdminMailInfoDetailsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
Revesion History : 2004/10/20  ����(���u) �쐬
*/
package webbroker3.mailinfo.message;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (���[�����ڍ׃��N�G�X�g)<BR>
 * ���[�����ڍ׃��N�G�X�g�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoDetailsRequest extends WEB3GenRequest
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
     private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoDetailsRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_details";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (���M���[���敪)<BR>
     */
    public String sendMailDiv;
    
    /**
     * (����ID)<BR>
     */
    public String discernId;

    /**
     * @@roseuid 416F1DCF030D
     */
    public WEB3AdminMailInfoDetailsRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * ���[�����ڍ׃��X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return webbroker3.mailinfo.common.WEB3GenResponse <BR>
     * @@roseuid 413C43CA0167
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoDetailsResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) ���M���[���敪�̃`�F�b�N<BR>
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
     * @@roseuid 413C441202CE
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //( 1) ���M���[���敪�̃`�F�b�N
        //(1-1) this.���M���[���敪==null�̏ꍇ�A��O���X���[����
        if (this.sendMailDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00862, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(1-2) this.���M���[���敪�̌�����4���̏ꍇ�A��O���X���[����B
        if(WEB3StringTypeUtility.getByteLength(this.sendMailDiv) > 4)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00863, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(1-3) this.���M���[���敪�̒l�����l�ȊO�̏ꍇ�A��O���X���[����B
        if ( WEB3StringTypeUtility.isNumber(this.sendMailDiv) == false)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00864, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
       //( 2) ����ID�̃`�F�b�N
       //(2-1) this.����ID==null�̏ꍇ�A��O���X���[����B
       if (this.discernId == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00865, 
               this.getClass().getName() + STR_METHOD_NAME); 
       }
       //(2-2) this.����ID�̌�����4���̏ꍇ�A�܂��͌���==0���̏ꍇ�A
       //��O���X���[����B
       if ((WEB3StringTypeUtility.getByteLength(this.discernId) > 4) || (WEB3StringTypeUtility.getByteLength(this.discernId) == 0 ))
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00866, 
               this.getClass().getName() + STR_METHOD_NAME); 
       }
       //( 2-3) this.����ID�̒l�ɁA���p�p���ȊO�̕��������݂���ꍇ�A
       //��O���X���[����B
       if (WEB3StringTypeUtility.isSingle(this.discernId) == false)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00867, 
               this.getClass().getName() + STR_METHOD_NAME); 
       }
       log.exiting(STR_METHOD_NAME);
    }
}
@