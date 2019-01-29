head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ʒm�����Q�ƃ\�[�g�L�[�N���X (WEB3AdminFrontSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27  ������ (���u) �d�l�ύX���f��No.120
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.define.WEB3AdminFrontMarketKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �ʒm�����Q�ƃ\�[�g�L�[�N���X<BR>
 */
public class WEB3AdminFrontSortKey extends Message 
{
 
	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminFrontSortKey.class);

   /**
    * �L�[����<BR>
    */
   public String keyItem;
   
   /**
    * �����^�~��<BR>
    * <BR>
    * A�F�@@����<BR>
    * D�F�@@�~��<BR>
    */
   public String ascDesc;
   
   /**
    * �R���X�g���N�^<BR>
    * @@roseuid 42D1F5140285
    */
   public WEB3AdminFrontSortKey() 
   {
    
   }
   
   /**
    * ���N���X�̐������`�F�b�N���s���B<BR>  
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>  
    * <BR>
    * �P�jthis.�L�[���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR> 
    * �@@�P�|�P�jthis.�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A<BR>  
    * �@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR> 
    * �@@�@@�@@�@@�@@�@@�E�ʒm��M���� <BR>
    * �@@�@@�@@�@@�@@�@@�E�f�[�^��ʃR�[�h <BR>
    * �@@�@@�@@�@@�@@�@@�E�����R�[�h <BR>
    * �@@�@@�@@�@@�@@�@@�E���X�R�[�h <BR>
    * �@@�@@�@@�@@�@@�@@�E�����R�[�h <BR>
    * �@@�@@�@@�@@�@@�@@�E���z�T�[�oNo<BR>
    * <BR>
    * �@@�@@�@@�@@class :�@@WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag �@@:�@@BUSINESS_ERROR_00086<BR>
    * <BR>
    * @@throws WEB3BusinessLayerException
    */
   public void validate() throws WEB3BusinessLayerException
   {
		final String STR_METHOD_NAME = ".validate()";
		log.entering(STR_METHOD_NAME);

        // �P�jthis.�L�[���� != null�̏ꍇ
        if (this.keyItem != null)
        {
            // �P�|�P�jthis.�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ
            if (!(WEB3AdminFrontMarketKeyItemDef.CREATED_TIMESTAMP).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.DATA_CLASS_CODE).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.ACCOUNT_CODE).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.BRANCH_CODE).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.PRODUCT_CODE).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.VIRTUAL_SERVER_NUMBER).equals(this.keyItem))
            {
                log.error("�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
   }
}
@
