head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.54.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���\�[�g�L�[(WEB3BondSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���\�[�g�L�[)<BR>
 * ���\�[�g�L�[
 * 
 * @@author  ��іQ
 * @@version 1.0
 */
public class WEB3BondSortKey extends Message 
{
    /** logger. */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSortKey.class);
    
    /**
     * (�L�[����)<BR>
     * �L�[����
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A�F�@@����<BR>
     * D�F�@@�~��
     */
    public String ascDesc;
    
    /**
     * (���\�[�g�L�[)<BR>
     * �R���X�g���N�^�B
     * @@roseuid 42194BCD0253
     */
    public WEB3BondSortKey() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�jthis.�L�[���� == null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B <BR>
     * �@@�@@tag:  BUSINESS_ERROR_00085<BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * <BR>
     * �Q�jthis.�����^�~�� == null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B <BR>
     * �@@�@@tag:  BUSINESS_ERROR_00087<BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * <BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E"A�F����"<BR>
     * �@@�@@�@@�E"D�F�~��"<BR>
     * �@@�@@�@@tag:  BUSINESS_ERROR_00088<BR>
     * �@@�@@�@@class:WEB3BusinessLayerException
     * @@throws  WEB3BaseException
     * @@roseuid 42194C070178
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�jthis.�L�[���� == null�̏ꍇ�A�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.keyItem))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�Q�jthis.�����^�~�� == null�̏ꍇ�A�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.ascDesc))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
