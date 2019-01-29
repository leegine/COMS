head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.47.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���j���[����\�[�g�L�[(WEB3AdminMCSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/22  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���j���[����\�[�g�L�[)<BR>
 * ���j���[����\�[�g�L�[<BR>
 * @@author �����@@
 * @@version 1.0
 */
 
public class WEB3AdminMCSortKeyUnit extends Message
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCSortKeyUnit.class); 

    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�ɑ΂��Ẵ��X�|���X�N���X���̃V���{�������L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 4198642A009C
     */
    public WEB3AdminMCSortKeyUnit()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�L�[���ڂ̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�L�[���ڂ�������l�̏ꍇ�A��O���X���[����B<BR>
     *               class   :  WEB3BusinessLayerException           <BR>
     *               tag     :  BUSINESS_ERROR_00085            <BR>
     * <BR>
     * �Q�j�@@�����^�~���̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����^�~���������͂̏ꍇ�A��O���X���[����B<BR>
     *               class   :  WEB3BusinessLayerException           <BR>
     *               tag     :  BUSINESS_ERROR_00087              <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *               class   :  WEB3BusinessLayerException           <BR>
     *               tag     :  BUSINESS_ERROR_00088               <BR>
     * <BR>
     * @@roseuid 41750D8D024A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
        //�P�j�@@�L�[���ڂ̃`�F�b�N<BR>
        //�@@�P�|�P�j�@@�L�[���ڂ�������l�̏ꍇ�A��O���X���[����B<BR>
        //              class   :  WEB3BusinessLayerException           <BR>
        //              tag     :  BUSINESS_ERROR_00085            <BR>
        if ((this.keyItem == null) || (this.keyItem == ""))
        {
            log.error(" �L�[���ڂ������� .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        // �Q�j�@@�����^�~���̃`�F�b�N<BR>
           // �Q�|�P�j�@@�����^�~���������͂̏ꍇ�A��O���X���[����B<BR>
           //             class   :  WEB3BusinessLayerException           <BR>
           //             tag     :  BUSINESS_ERROR_00087              <BR>
           if ((this.ascDesc == null) || (this.ascDesc == ""))
           {
               log.error("�����^�~����������. ");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                               this.getClass().getName() + STR_METHOD_NAME);
           }
           // �@@�Q�|�Q�j�@@�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
           //               class   :  WEB3BusinessLayerException           <BR>
           //               tag     :  BUSINESS_ERROR_00088               <BR>
           if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
           {
               log.error("�����^�~�����s���ȃR�[�h�l. ");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                               this.getClass().getName() + STR_METHOD_NAME);
           }

           log.exiting(STR_METHOD_NAME);
     }
}
@
