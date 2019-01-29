head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCTransactionCategoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : (�@@�\�J�e�S�����(WEB3AdminMCTransactionCategoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���q (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;



/**
 * (�@@�\�J�e�S�����)<BR>
 * �@@�\�J�e�S�����<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3AdminMCTransactionCategoryUnit extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCTransactionCategoryUnit.class);

    /**
     * (�@@�\�J�e�S���R�[�h)<BR>
     * �@@�\�J�e�S���R�[�h<BR>
     * <BR>
     * �� �����̂���@@�\�J�e�S���R�[�h<BR>
     * <BR>
     */
    public String transactionCategory;
    
    /**
     * (�X�V���t���O)<BR>
     * �X�V���t���O<BR>
     * <BR>
     * �X�V�������\�ȊǗ��҂̏ꍇtrue<BR>
     * �X�V�������s�̊Ǘ��҂̏ꍇfalse<BR>
     * <BR>
     */
    public boolean updatePermissionFlag;
    
    /**
     * @@roseuid 4198642A0177
     */
    public WEB3AdminMCTransactionCategoryUnit() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����<BR>
     * <BR>
     * �P�j�@@�@@�\�J�e�S���R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01220           <BR>
     * <BR>
     * @@roseuid 41760FBE02D1
     */
    public void validate() throws WEB3BaseException
    {
         final String STR_METHOD_NAME = " validate()"; 
         log.entering(STR_METHOD_NAME);
         // �P�j�@@�@@�\�J�e�S���R�[�h�����͂̏ꍇ
         if (this.transactionCategory == null || "".equals(this.transactionCategory))
         {
             //��O
             log.error("�u�@@�\�J�e�S���R�[�h�����́v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01220,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
           
         log.exiting(STR_METHOD_NAME);
    }
}
@
