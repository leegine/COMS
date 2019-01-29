head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecEndNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���I���ʒm���N�G�X�g�N���X(WEB3IfoExecEndNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
Revesion History : 2007/11/19 �И��� �d�l�ύX���f��No.796,814
*/

package webbroker3.ifo.message;


import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�o���I���ʒm���N�G�X�g)<BR>
 * �敨OP�o���I���ʒm���N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoExecEndNotifyRequest extends WEB3BackRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "ifo_execEndNotify";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112125L;

    /**
     * (�،���ЃR�[�h)<BR>
     * �o���I�������̑ΏۂƂȂ�،���ЃR�[�h�B<BR>
     */
    public String institutionCode;

    /**
     * (From����ID)<BR>
     */
    public long rangeFrom;

    /**
     * (To����ID)<BR>
     */
    public long rangeTo;

    /**
     * (�敨�^�I�v�V�����敪)<BR>
     * 0�FDEFAULT�@@1�F�敨�@@2�F�I�v�V���� <BR>
     * �iWEB3FuturesOptionDivDef�ɂĒ�`�j<BR>
     */
    public String fuOpDiv;

    /**
     * (�o���I���敪)<BR>
     * 1�F�[��O�o���I���i�[����{�����Ёj�@@DEFAULT 0�i�o���I���i�ŏI�j�j<BR>
     */
    public String execEndDiv;

    /**
     * @@roseuid 40C0AE4E01D4
     */
    public WEB3IfoExecEndNotifyRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3IfoExecEndNotifyResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j �،���ЃR�[�h�`�F�b�N<BR>
     * <BR>
     * �@@this.�،���ЃR�[�h == null �̏ꍇ�A�u�،���ЃR�[�h��null�v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02775<BR>
     * <BR>
     * �Q�j �敨�^�I�v�V�����敪�`�F�b�N<BR>
     * <BR>
     * �@@this.�敨�^�I�v�V�����敪 != �i"1�F�敨" or "2�F�I�v�V����"�j �̏ꍇ�A<BR>
     * �@@�u�敨�^�I�v�V�����敪���s���ł��v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02953<BR>
     * <BR>
     * �R�j �o���I���敪�`�F�b�N<BR>
     * �@@this.�o���I���敪 != �i"1�F�[��O�o���I��" or "0�F�o���I���i�ŏI�j"�j �̏ꍇ�A<BR>
     * �@@�u�o���I���敪���s���ł��v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02954<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�،���ЃR�[�h�`�F�b�N
        if (this.institutionCode == null)
        {
            //this.�،���ЃR�[�h == null �̏ꍇ
            //�u�،���ЃR�[�h��null�v�̗�O��throw����B
            log.debug("�،���ЃR�[�h��null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02775,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h��null");
        }

        //�敨�^�I�v�V�����敪�`�F�b�N
        //this.�敨�^�I�v�V�����敪 != �i"1�F�敨" or "2�F�I�v�V����"�j �̏ꍇ
        if (!WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv)
            && !WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv))
        {
            //�u�敨�^�I�v�V�����敪���s���ł��v�̗�O��throw����B
            log.debug("�敨�^�I�v�V�����敪���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02953,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�敨�^�I�v�V�����敪���s���ł��B");
        }

        //�o���I���敪�`�F�b�N
        //this.�o���I���敪 != �i"1�F�[��O�o���I��" or "0�F�o���I���i�ŏI�j"�j �̏ꍇ
        if (!WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END.equals(this.execEndDiv)
            && !WEB3OrderexecutionEndTypeDef.DEFAULT.equals(this.execEndDiv))
        {
            //�u�o���I���敪���s���ł��v�̗�O��throw����
            log.debug("�o���I���敪���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02954,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�o���I���敪���s���ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
