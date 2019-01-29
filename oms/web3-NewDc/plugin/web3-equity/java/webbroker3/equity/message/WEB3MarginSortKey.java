head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����\�[�g�L�[(WEB3MarginSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����\�[�g�L�[�j�B<BR>
 * <BR>
 * �M�p����\�[�g�L�[�N���X<BR>
 * �ꗗ�\�����̃\�[�g������N���X�B<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSortKey extends Message 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSortKey.class);

    /**
     * (�L�[����)<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{�������L�[<BR>
     * ���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * <BR>
     * A�F�����@@D�F�~��
     */
    public String ascDesc;
    
    /**
     * (�M�p����\�[�g�L�[)<BR>
     * <BR>
     * �R���X�g���N�^�B
     * @@return WEB3MarginSortKey
     * @@roseuid 40C9313003DF
     */
    public WEB3MarginSortKey() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00085<BR>
     * �Q�jthis.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00087<BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41103B36019A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3MarginSortKey: validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00085,
            this.getClass().getName() + "validate");
        }

        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00087,
            this.getClass().getName() + "validate");
        }

        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00088,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
