head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3IndexQuoteDataImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �w���p�������N���X(WEB3IndexQuoteDataImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
                   2009/04/20 ���@@�@@�@@�@@��(FLJ) �����V�X�e���֑ؑΉ�
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.quoteadaptor.*;

/**
 * �w���p�������N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3IndexQuoteDataImpl
    extends AbstractWEB3QuoteData
    implements WEB3IndexQuoteData
{
    
    IndexType indexType;

    /**
     * �R���X�g���N�^
     */
    WEB3IndexQuoteDataImpl(IndexType indexType, RealType realType)
    {
        super();
        super.quoteDate = WEB3QuoteUtil.getSystemDate();
        super.realType = realType;
        super.dataType = DataType.INDEX;
        super.productCode = WEB3QuoteProductCodes.toIndexProductCode(indexType);
        this.indexType = indexType;
    }
    
    public IndexType getIndexType()
    {
        return indexType;
    }

    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData#getQuoteTimestamp()
     */
    public Timestamp getQuoteTimestamp()
    {
        return getCurrentPriceTime();
    }

}
@
