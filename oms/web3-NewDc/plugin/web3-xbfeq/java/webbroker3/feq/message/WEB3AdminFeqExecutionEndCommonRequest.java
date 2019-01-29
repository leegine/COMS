head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o���I�����ʃ��N�G�X�g(WEB3AdminFeqExecutionEndCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҊO�������o���I�����ʃ��N�G�X�g)<BR>
 * �Ǘ��ҊO�������o���I�����ʃ��N�G�X�g�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionEndCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionEndCommonRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionEndCommon";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;   
    
    /**
     * (�o���I�����{�ꗗ)<BR>
     * �O�������o���I�����{�����̔z��
     */
    public WEB3FeqExecutionEndExecuteCondUnit[] executionEndExecuteCondList;
    
    /**
     * @@roseuid 42CE39FE0213
     */
    public WEB3AdminFeqExecutionEndCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@this.�o���I�����{�ꗗ == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02031<BR>
     * <BR>
     * �Q�j�@@this.�o���I�����{�ꗗ�̗v�f�����A�ȉ��̏��������{����B<BR>
     * �@@�Q�|�P�j�����Ώۂ̗v�f.validate()���R�[������B
     * @@throws WEB3BaseException
     * @@roseuid 42AFEFD002A7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@this.�o���I�����{�ꗗ == null�̏ꍇ�A��O���X���[����B
        if (this.executionEndExecuteCondList == null || 
            this.executionEndExecuteCondList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02031,
                this.getClass().getName() + STR_METHOD_NAME,
                " �o���I�����{�ꗗ�����݂��܂���B"); 
        }
        
        //�Q�j�@@this.�o���I�����{�ꗗ�̗v�f�����A�ȉ��̏��������{����B
        //�Q�|�P�j�����Ώۂ̗v�f.validate()���R�[������B
        int l_intCnt = this.executionEndExecuteCondList.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3FeqExecutionEndExecuteCondUnit l_unit = executionEndExecuteCondList[i];
            if (l_unit != null)
            {
                l_unit.validate();
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
        return null;
    }
}
@
