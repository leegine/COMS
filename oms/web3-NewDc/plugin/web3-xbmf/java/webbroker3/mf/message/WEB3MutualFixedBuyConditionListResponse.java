head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�����ꗗ���X�|���X(WEB3MutualFixedBuyConditionListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�莞��z���t�����ꗗ���X�|���X)<BR>
 * ���M�莞��z���t�����ꗗ���X�|���X<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200607221353L; 
   
    /**
     *(���M�����J�e�S���[�ꗗ)<BR>
     *<BR>
     *���M�����J�e�S���[�ꗗ<BR>
     *(null�̏ꍇ�̓J�e�S���w�薳���Ƃ���)<BR>
     */
    public WEB3MutualProductCategoryUnit[] categoryList;
    
    /**
     * (���M�莞��z���t�����ꗗ)<BR>
     * ���M�莞��z���t�����ꗗ<BR>
     */
    public WEB3MutualFixedBuyConditionUnit[] conditionList;
    
    /**
     * (�莞��z���t���z���v)<BR>
     * �莞��z���t���z���v�N���X�̔z��<BR>
     * <BR>
     * �莞��z���t���z���v[0] �F�@@1�Ԗڂ̍��v <BR>
     * �莞��z���t���z���v[1] �F�@@2�Ԗڂ̍��v <BR>
     */
    public WEB3MutualFixedBuyTotalUnit[] totalList;
    
    /**
     * (�莞��z�����������)<BR>
     * �莞��z�����������<BR>
     */
    public WEB3MutualFixedBuyAccountInfo acountInfo;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MutualFixedBuyConditionListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
   
    /**
     * (���M�莞��z���t�����ꗗ���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyConditionListResponse() 
    {     
    }  
}
@
