import Link from 'next/link';
import React from 'react';

import { Section, SectionText, SectionTitle } from '../../styles/GlobalComponents';
import Button from '../../styles/GlobalComponents/Button';
import { LeftSection } from './HeroStyles';

const Hero = () => (
  <Section row nopadding>
    <LeftSection>
      <SectionTitle main center>
        Hey There,<br/>
        I'm Joseph Evans
      </SectionTitle>
      <SectionText>
        Full Stack Web & Mobile Applications Developer
      </SectionText>
      <Button onClick={() => window.location = 'mailTo: Josephev4@gmail.com'}>Learn More</Button>
    </LeftSection>
  </Section>
);

export default Hero;