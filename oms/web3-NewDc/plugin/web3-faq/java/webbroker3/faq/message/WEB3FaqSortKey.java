head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ��\�[�g�L�[(WEB3FaqSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�⍇���Ǘ��\�[�g�L�[)<BR>
 * �⍇���Ǘ��\�[�g�L�[<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3FaqSortKey extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqSortKey.class);
        
    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�ɑ΂��Ẵ��X�|���X�N���X���̃V���{������<BR>
     * �L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 41C25C09000F
     */
    public WEB3FaqSortKey() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�L�[���ڂ̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�L�[���ڂ�������l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j�@@�����^�~���̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����^�~���������͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00087<BR>
     * �@@�Q�|�Q�j�@@�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A6FBF503D5
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
       
        //�P�j�@@�L�[���ڂ̃`�F�b�N
        //�P�|�P�j�@@�L�[���ڂ������͂̏ꍇ�A��O���X���[����B

        if (WEB3StringTypeUtility.isEmpty(keyItem))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                this.getClass().getName(),
                "�L�[���ڂ������́B");
        }
        
        //�Q�j�@@�����^�~���̃`�F�b�N
        //�Q�|�P�j�@@�����^�~���������͂̏ꍇ�A��O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmpty(ascDesc))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                this.getClass().getName(),
                "�����^�~���������́B");
        }    
        
        //�Q�|�Q�j�@@�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
        if (!(WEB3AscDescDef.ASC.equals(ascDesc)) && !(WEB3AscDescDef.DESC.equals(ascDesc)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                this.getClass().getName(),
                "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B" + "[�����^�~��] = " + ascDesc
                );
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
