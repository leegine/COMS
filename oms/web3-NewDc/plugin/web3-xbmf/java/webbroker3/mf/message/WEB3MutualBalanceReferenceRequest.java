head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�c���Ɖ�N�G�X�g�N���X(WEB3MutualBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ������ (���u) �V�K�쐬
Revesion History : 2006/03/08 ��� (SRA) �d�l�ύX�i���f���j�F403
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��536
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M�c���Ɖ�N�G�X�g)<BR>
 * ���M�c���Ɖ�N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceRequest extends WEB3GenRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_balance_reference";
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412281618L;

    /**
    * (�v���y�[�W�ԍ�)<BR>
    * �v���y�[�W�ԍ�<BR>
    * �\�����������y�[�W�ʒu���w��<BR>
    * ���擪�y�[�W��"1"�Ƃ���
    */
   public String pageIndex;
   
   /**
    * (�y�[�W���\���s��)<BR>
    * �y�[�W���\���s��<BR>
    * 1�y�[�W���ɕ\�����������s�����w��
    */
   public String pageSize;
   
   /**
    * (���M�\�[�g�L�[)<BR>
    * ���M�\�[�g�L�[<BR>
    * <BR>
    * �Ώۍ���:�h�����h�A�h�]���z�h�A�h�]�����v�h�A�h������t���؎��ԁh�A
    * �@@�@@�@@�@@ �h����ID�h
    */
   public WEB3MutualSortKey[] sortKeys;

    /**
     * (���M��O��MMF�\���敪)<BR>
     * ���M��O��MMF�\���敪 <BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪 <BR>
     * <BR>
     * 0:���M�̂� <BR>
     * 1:�O��MMF�̂� <BR>
     * 2:���� <BR>
     * <BR>
     * ��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

   /**
    * @@roseuid 41D13CC2000F
    */
   public WEB3MutualBalanceReferenceRequest() 
   {
    
   }
   
   /**
    * (validate)<BR>
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
    * �@@�P�|�P�j�@@this.�v���y�[�W�ԍ�==null�̏ꍇ�A��O���X���[����B<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_00089 <BR>
    * �@@�P�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̏ꍇ�A��O���X���[����B<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_00090 <BR>
    * <BR>
    * �Q�j�@@�y�[�W���\���s���`�F�b�N<BR>
    * �@@�Q�|�P�j�@@this.�y�[�W���\���s��==null�̏ꍇ�A��O���X���[����B<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_00091 <BR>
    * �@@�Q�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̏ꍇ�A��O���X���[����B<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_00092 <BR>
    * <BR>
    * �R)�@@���M�\�[�g�L�[�`�F�b�N<BR>
    * �@@�R�|�P)�@@this.���M�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B<BR>
    *             class: WEB3BusinessLayerException<BR>
    *             tag:   BUSINESS_ERROR_00231 <BR>
    * �@@�R�|�Q)�@@this.���M�\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR>
    *             class: WEB3BusinessLayerException<BR>
    *             tag:   BUSINESS_ERROR_00232 <BR>
    * �@@�R�|�R)�@@this.���M�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
    * �@@�@@�R�|�R�|�P�j�@@�L�[����==null�̏ꍇ�A��O���X���[����B<BR>
    *             class: WEB3BusinessLayerException<BR>
    *             tag:   BUSINESS_ERROR_00085 <BR>
    * �@@�@@�R�|�R�|�Q�j�@@�L�[���ڂ��ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E���M�c���Ɖ��.�����敪<BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E���M�c���Ɖ��.�]���z<BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E���M�c���Ɖ��.�]�����v<BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E���M�c���Ɖ��.������t���؎���<BR>
    *�@@�@@�@@�@@�@@�@@�@@�@@�@@ �E���M�c���Ɖ��.����ID<BR>
    *             class: WEB3BusinessLayerException<BR>
    *             tag:   BUSINESS_ERROR_00086 <BR>
    * <BR>
    * �@@�@@�R�|�R�|�R�j�@@�����^�~��==null�̏ꍇ�A��O���X���[����B<BR>
    *                class: WEB3BusinessLayerException<BR>
    *                tag:BUSINESS_ERROR_00087<BR>
    * �@@�@@�R�|�R�|�S�j�@@�����^�~�����ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B
    * <BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�����h<BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�~���h<BR>
    *                class: WEB3BusinessLayerException<BR>
    *                tag:BUSINESS_ERROR_00088
    * @@throws WEB3BaseException
    * @@roseuid 41AD8AD902B0
    */
   public void validate() throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "validate()";
       log.entering(STR_METHOD_NAME);
       
       // �P�j�@@�v���y�[�W�ԍ��`�F�b�N
       // �@@�P�|�P�j�@@this.�v���y�[�W�ԍ�==null�̏ꍇ�A��O���X���[����B
       if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
       {
           log.debug("�v���y�[�W�ԍ������w��ł��B");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00089,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�v���y�[�W�ԍ������w��ł��B");    
       }
       
       // �@@�P�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̏ꍇ�A��O���X���[����B
       if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
       {
           log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog. BUSINESS_ERROR_00090,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�v���y�[�W�ԍ��������ȊO�̒l�ł���B" +
               "�v���y�[�W�ԍ� = " + this.pageIndex);
       }

       // �Q�j�@@�y�[�W���\���s���`�F�b�N
       // �@@�Q�|�P�j�@@this.�y�[�W���\���s��==null�̏ꍇ�A��O���X���[����B
       if (WEB3StringTypeUtility.isEmpty(this.pageSize))
       {
           log.debug("�y�[�W���\���s�������w��ł��B");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00091 ,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�y�[�W���\���s�������w��ł��B");
       }
       
       // �@@�Q�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̏ꍇ�A��O���X���[����B
       if (!WEB3StringTypeUtility.isNumber(this.pageSize))
       {
           log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog. BUSINESS_ERROR_00092,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�y�[�W���\���s���������ȊO�̒l�ł��B" +
               "�y�[�W���\���s�� = " + this.pageSize);
       }

       // �R)�@@���M�\�[�g�L�[�`�F�b�N
       // �@@�R�|�P)�@@this.���M�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B
       if (this.sortKeys == null)
       {
           log.debug("�\�[�g�L�[�����w��ł��B");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00231,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�\�[�g�L�[�����w��ł��B"); 
       }
       
       // �@@�R�|�Q)�@@this.���M�\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B
       if (this.sortKeys.length == 0)
       {
           log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00232,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�\�[�g�L�[�̗v�f�����O�ł��B");
       }      
       
       // �@@�R�|�R)�@@this.���M�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
       for (int i=0; i < this.sortKeys.length; i++)
       {
           WEB3MutualSortKey l_mutualSortKey = this.sortKeys[i];
          
           //�R�|�R�|�P�j�@@�L�[���ڂ�null�̒l�ł���Η�O���X���[����B
           if (WEB3StringTypeUtility.isEmpty(l_mutualSortKey.keyItem))
           {
               log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "�\�[�g�L�[�̃L�[���ڂ����w��ł��B"); 
           }
           
		   // �R�|�R�|�Q�j�@@�L�[���ڂ��ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B
		   // �@@�@@�E���M�c���Ɖ��.�����敪
		   // �@@�@@�E���M�c���Ɖ��.�]���z
		   // �@@�@@�E���M�c���Ɖ��.�]�����v
		   // �@@�@@�E���M�c���Ɖ��.������t���؎���
           //�@@�@@ �E���M�c���Ɖ��.����ID
           if (!((l_mutualSortKey.keyItem).equals(
                   WEB3MFSortkeyItemDef.TAX_TYPE) || 
               (l_mutualSortKey.keyItem).equals(
                   WEB3MFSortkeyItemDef.MARKET_VALUE) || 
               (l_mutualSortKey.keyItem).equals(
                   WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS) ||
               (l_mutualSortKey.keyItem).equals(
                   WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME)||
               (l_mutualSortKey.keyItem).equals(WEB3MFSortkeyItemDef.MUTUAL_PRODUCT_ID)))
           {
               log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "�L�[���ڂ��h�����敪�h�A�h�]���z�h�A�h�]�����v�h�A�h������t���؎��ԁh�A" +
                   "�h����ID�h�ȊO�̒l�ł���" +
                   "�L�[���� = " + l_mutualSortKey.keyItem);     
           }
           
           // �@@�@@�R�|�R�|�R�j�@@�����^�~��==null�̏ꍇ�A��O���X���[����B
           if(WEB3StringTypeUtility.isEmpty(l_mutualSortKey.ascDesc))
           {
               log.debug("�����^�~�������w��ł��B");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "�����^�~�������w��ł��B");  
           }
           
           // �@@�@@�R�|�R�|�S�j�@@�����^�~�����ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B
           // �@@�@@�@@�@@�E�h�����h 
           // �@@�@@�@@�@@�E�h�~���h 
           if((!WEB3AscDescDef.ASC.equals(l_mutualSortKey.ascDesc))
                   && (!WEB3AscDescDef.DESC.equals(l_mutualSortKey.ascDesc)))
           {
               log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B" +
                   "�����^�~���̒l = " + l_mutualSortKey.ascDesc); 
           }
      }
      log.exiting(STR_METHOD_NAME);
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    *<BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3MutualBalanceReferenceResponse(this);
   }
}
@
