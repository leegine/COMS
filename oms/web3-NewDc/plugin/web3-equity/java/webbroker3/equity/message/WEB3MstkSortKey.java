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
filename	WEB3MstkSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����\�[�g�L�[(WEB3MstkSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 �d��(���u) �V�K�쐬
                   2005/01/05 ����(SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j�����\�[�g�L�[�j�B<br>
 * <br>
 * �ꗗ�\�����̃\�[�g������N���X
 * @@author �d��
 * @@version 1.0
 */
public class WEB3MstkSortKey extends Message 
{
    
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSortKey.class);

    
    /**
     * �i�L�[���ځj�B<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�ɑ΂��Ẵ��X�|���X�N���X��<BR>
     * �̃V���{�������L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB
     */
    public String keyItem;
    
    /**
     * �i�����^�~���j�B<BR>
     * <BR>
     * A�F�����@@D�F�~��
     */
    public String ascDesc;
    
    /**
     * 
     */
    public WEB3MstkSortKey() 
    {
     
    }
    
    /**
     * �ivalidate�j�B<BR>
     * <BR>
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�jthis.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * <BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        log.debug("�����~�\�[�g�L�[�̃`�F�b�N�F BEGIN");
        log.debug("�P�jthis.�L�[���ځ�null�̃`�F�b�N: BEGIN");
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
 
     
    }
}
@
