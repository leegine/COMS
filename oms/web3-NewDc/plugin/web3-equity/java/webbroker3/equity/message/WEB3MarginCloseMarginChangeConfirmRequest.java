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
filename	WEB3MarginCloseMarginChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p�����������_�ԍϊm�F���N�G�X�g�N���X(WEB3MarginCloseMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/15 ������ (���u) �V�K�쐬
                   2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�Q�V�O
                   2004/12/21 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�����������_�ԍϊm�F���N�G�X�g�j�B<br>
 * <br>
 * �M�p�����������_�ԍϊm�F���N�G�X�g�N���X
 * @@version 1.0
 */
public class WEB3MarginCloseMarginChangeConfirmRequest extends WEB3MarginCommonRequest 
{
    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginCloseMarginChangeConfirmRequest.class);

    /**
     * <p>�iPTYPE�j�B</p>
     */
    public static final String PTYPE = "margin_closeMarginChangeConfirm";

    /**
     * <p>�iSerialVersionUID�j�B</p>
     */
    public static final long serialVersionUID = 200409101638L;

    /**
     * <p>�iID�j�B</p>
     * <p>�����h�c</p>
     */
    public String id;
    
    /**
     * <p>�i���ό����ꗗ�j�B</p>
     * <p>�M�p������ό������ׂ̈ꗗ</p>
     */
    public WEB3MarginCloseMarginContractUnit[] closeMarginContractUnits;
    
    /**
     * <p>�i�M�p�����������_�ԍϊm�F���N�G�X�g�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3MarginCloseMarginChangeConfirmRequest() 
    {
    }
    
    /**
     * <p>�ivalidate�j�B</t>
     * <p>�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<br>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<br>
     * <br>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���R�[������B<br>
     * <br>
     * �Q�j�@@ID�`�F�b�N<br>
     * �@@�@@�@@this.ID��null�̏ꍇ�A�uID��null�v�̗�O���X���[����B<br>
     * �@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@tag:�@@ BUSINESS_ERROR_00080<br>
     * <br>
     * �R�j�@@���ό����ꗗ�`�F�b�N<br>
     * �@@�R�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:�@@ BUSINESS_ERROR_00610<br>
     * <br>
     * �@@�R�|�Q�jthis.���ό����ꗗ���v�f�����O�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:�@@ BUSINESS_ERROR_00611<br>
     * <br>
     * �S�j�@@���ό����ꗗ�v�f���`�F�b�N<br>
     * �@@�@@�@@�@@this.���ό����ꗗ�̗v�f(���ό�������)�����A<br>
     * �@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B<br>
     * �@@�@@�@@�@@�E���ό������ׂ�validate( )���\�b�h���R�[������B<br>
     * <br>
     * �T�j�@@���ό����ꗗ�������`�F�b�N<br>
     * �@@�T�|�P�j�isuper.�������� == null�j�A����<br>
     * �@@�@@�@@�@@�@@�ithis.���ό����ꗗ�̗v�f�����̑S�Ă̒���������null�܂���0�j�̏ꍇ�A <br>
     * �@@�@@�@@�@@�@@�u���������w��Ȃ��v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:�@@ BUSINESS_ERROR_00612</p>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        
        log.entering(STR_METHOD_NAME);
        // �P�j�@@�X�[�p�[�N���X��validate���\�b�h���R�[������B
        super.validate();
        
        // �Q�j�@@ID�`�F�b�N
        // �@@�@@�@@this.ID��null�̏ꍇ�A�uID��null�v�̗�O���X���[����B
        // �@@�@@�@@class: WEB3BusinessLayerException
        // �@@�@@�@@tag:�@@ BUSINESS_ERROR_00080
        if (id == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
        
        // �R�j�@@���ό����ꗗ�`�F�b�N
        // �@@�R�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B
        // �@@�@@�@@�@@�@@class: WEB3BusinessLayerException
        // �@@�@@�@@�@@�@@tag:�@@ BUSINESS_ERROR_00610     
        // <BR>
        // �@@�R�|�Q�jthis.���ό����ꗗ���v�f�����O�̏ꍇ�A
        // �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B
        // �@@�@@�@@�@@�@@class: WEB3BusinessLayerException
        // �@@�@@�@@�@@�@@tag:�@@ BUSINESS_ERROR_00611     
        if (closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00610,STR_METHOD_NAME);
        }
        if (closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00611,STR_METHOD_NAME);
        }
        
        // �S�j�@@���ό����ꗗ�v�f���`�F�b�N<BR>
        // �@@�@@�@@�@@this.���ό����ꗗ�̗v�f(���ό�������)�����A<BR>
        // �@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B<BR>
        // �@@�@@�@@�@@�E���ό������ׂ�validate( )���\�b�h���R�[������B<BR>
        for (int i = 0;i < closeMarginContractUnits.length;i++)
        {
            closeMarginContractUnits[i].validate();
        }
        
        // �T�j�@@���ό����ꗗ�������`�F�b�N<BR>
        // �@@�T-�P�j�isuper.�������� == null�j�A����<BR>
        // �@@�@@�@@�@@�@@�ithis.���ό����ꗗ�̗v�f�����̑S�Ă̒���������null�܂���0�j�̏ꍇ�A <BR>
        // �@@�@@�@@�@@�@@�u���������w��Ȃ��v�̗�O���X���[����B<BR>
        // �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
        // �@@�@@�@@�@@�@@tag:�@@ BUSINESS_ERROR_00612<BR>   
        if(super.orderQuantity == null)
        {
            boolean l_orderQuantityFlg = false;
            for(int i = 0 ; i < closeMarginContractUnits.length ; i++)
            {
                if(closeMarginContractUnits[i].orderQuantity != null 
                && Long.parseLong(closeMarginContractUnits[i].orderQuantity) > 0)
                {
                    l_orderQuantityFlg = true;
                }
            }
            
            if(!l_orderQuantityFlg) {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00612,STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validateAT���Ύ��)<BR>
     * ���Ύ���w�莞�́A�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�A�������p�̃��\�b�h�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�@@ID�`�F�b�N<BR>
     * �@@�@@this.ID��null�̏ꍇ�A�uID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@ BUSINESS_ERROR_00080<BR>
     * <BR>
     * �R�j�@@���ό����ꗗ�`�F�b�N<BR>
     * �@@�R�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@ BUSINESS_ERROR_00610<BR>
     * <BR>
     * �@@�R�|�Q�jthis.���ό����ꗗ���v�f�����O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@ BUSINESS_ERROR_00611<BR>
     * <BR>
     * �S�j�@@�A�������E���������`�F�b�N<BR>
     * �@@�X�[�p�[�N���X��validate�A���������\�b�h���R�[������B<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if (id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00610,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00611,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        super.validateSuccOrder();
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * <p>�icreate���X�|���X�j�B</p>
     * <p>�M�p�����������_�ԍϊm�F���X�|���X�𐶐����ĕԂ��B</p>
     * @@return �M�p�����������_�ԍϊm�F���X�|���X
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3MarginCloseMarginChangeConfirmResponse(this);
    }
}
@
