head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������\�[�g�L�[(WEB3EquitySortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �����F (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i���������\�[�g�L�[�j�B<BR>
 * <BR>
 * �ꗗ�\�����̃\�[�g������N���X
 * @@version 1.0
 */
public class WEB3EquitySortKey extends Message
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySortKey.class);

    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X����<BR>
     * �V���{�������L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * 
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;

    /**
     * (���������\�[�g�L�[)<BR>
     * @@roseuid 40A118170177
     */
    public WEB3EquitySortKey()
    {

    }
    
    /**
     * �ivalidate�j<BR>
     * <BR>
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00085<BR>
     * �Q�jthis.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00087<BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00088<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�L�[���ڂ�Null�`�F�b�N");
        if(this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + ".validate()");
        }
        
        log.debug("�����^�~����Null�`�F�b�N");
        if(this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + ".validate()");
        }
        
        log.debug("�����^�~���̍��ڃ`�F�b�N");
        if(!WEB3AscDescDef.ASC.equals(this.ascDesc)
            && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + ".validate()");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
