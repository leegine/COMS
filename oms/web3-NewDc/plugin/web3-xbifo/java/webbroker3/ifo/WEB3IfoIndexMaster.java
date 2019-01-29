head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoIndexMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�w���}�X�^�N���X(WEB3IfoIndexMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���� �V�K�쐬
              001: 2004/08/13 ���Ō� �Ή��o�O BUG94  
                       public WEB3IfoIndexMaster(String l_strUnderlyingProductCode,String l_strFuturesOptionDiv)��ǉ� 
Revesion History : 2008/07/24 ���z (���u) �d�l�ύX ���f��888
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;

import webbroker3.ifo.data.IfoIndexMasterRow;
import webbroker3.ifo.data.IfoIndexMasterDao;


/**
 * (�敨OP�w���}�X�^)<BR>
 * �敨OP�w���}�X�^�N���X<BR>
 * (DB���C�A�E�g �u�敨OP�w���}�X�^�e�[�u��.xls�v�Q��)<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3IfoIndexMaster 
{

    /**
     * (�敨OP�w���}�X�^Row)<BR>
     * <BR>
     * �敨OP�w���}�X�^�s�I�u�W�F�N�g<BR>
     * �i��������DAO�N���X�j<BR>
     */
    private IfoIndexMasterRow futuresOptionIndexMasterRow;
    
    /**
     * @@roseuid 40C075110203
     */
    public WEB3IfoIndexMaster() 
    {
     
    }
    
    /**
     * (�敨OP�w���}�X�^)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * �����̎w���h�c�Ɉ�v����敨OP�w���}�X�^�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l�ɂĐ敨OP�w���}�X�^�e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�敨OP�w���}�X�^Row�j��<BR>
     * this.�敨OP�w���}�X�^Row�ɃZ�b�g����B<BR>
     * @@param l_lngIndexID - �w���h�c
     * @@return webbroker3.ifo.WEB3IfoIndexMaster
     * @@roseuid 405E73F302D3
     */
    public WEB3IfoIndexMaster(long l_lngIndexID) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {
        futuresOptionIndexMasterRow = 
            IfoIndexMasterDao.findRowByPk(l_lngIndexID);       
       
    }

    /**
     * (�敨OP�w���}�X�^)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * �����̌����Y�����R�[�h�A�敨/�I�v�V�����敪�Ɉ�v����敨OP�w���}�X�^�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l�ɂĐ敨OP�w���}�X�^�e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�敨OP�w���}�X�^Row�j��<BR>
     * this.�敨OP�w���}�X�^Row�ɃZ�b�g����B<BR>
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     * @@param l_strFuturesOptionDiv - �敨�^�I�v�V�����敪
     * @@return webbroker3.ifo.WEB3IfoIndexMaster
     * @@roseuid 405E73F302D3
     */
    public WEB3IfoIndexMaster(String l_strUnderlyingProductCode,String l_strFuturesOptionDiv) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {
        futuresOptionIndexMasterRow = 
            IfoIndexMasterDao.findRowByUnderlyingProductCodeFutureOptionDiv(l_strUnderlyingProductCode, l_strFuturesOptionDiv);           
    }
        
    /**
     * this.�敨OP�w���}�X�^Row��ԋp����B<BR>
     * @@return Object
     * @@roseuid 405E73F302D2
     */
    public Object getDataSourceObject() 
    {
        return futuresOptionIndexMasterRow;
    }

}
@
