head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	QuoteDataQueryParamsSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������̌���������\���C���^�[�t�F�[�X(QuoteQueryParamsSpec.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/14 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.quoteadaptor.prototype;

import com.fitechlabs.xtrade.kernel.data.DataException;

import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoRow;

/**
 * �������̌���������\���C���^�[�t�F�[�X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
interface QuoteDataQueryParamsSpec
{
    
    /**
     * SQL��where���\����������擾����B
     * 
     * @@return SQL��where���\��������
     */
    public String getQueryString();
    
    /**
     * SQL�Ƀo�C���h����I�u�W�F�N�g�̔z����擾����B
     * 
     * @@return SQL�Ƀo�C���h����I�u�W�F�N�g�̔z����擾����B
     */
    public Object[] getBindVars();
    
    /**
     * �V����Web3QuoteProtoRow�̃C���X�^���X�𐶐�����B
     * 
     * @@return Web3QuoteProtoRow�̃C���X�^���X
     */
    public Web3QuoteProtoRow newWeb3QuoteProtoRow() throws DataException;

}
@
